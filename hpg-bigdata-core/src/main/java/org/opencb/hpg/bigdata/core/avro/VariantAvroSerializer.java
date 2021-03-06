package org.opencb.hpg.bigdata.core.avro;

import htsjdk.variant.variantcontext.VariantContext;
import htsjdk.variant.vcf.VCFHeader;
import org.apache.commons.lang3.StringUtils;
import org.opencb.biodata.models.core.Region;
import org.opencb.biodata.models.metadata.Cohort;
import org.opencb.biodata.models.metadata.SampleSetType;
import org.opencb.biodata.models.variant.avro.VariantAvro;
import org.opencb.biodata.models.variant.metadata.VariantStudyMetadata;
import org.opencb.biodata.tools.variant.VcfFileReader;
import org.opencb.biodata.tools.variant.converters.avro.VariantContextToVariantConverter;
import org.opencb.biodata.tools.variant.metadata.VariantMetadataManager;
import org.opencb.commons.run.ParallelTaskRunner;
import org.opencb.hpg.bigdata.core.io.ConvertEncodeTask;
import org.opencb.hpg.bigdata.core.io.avro.AvroFileWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;

/**
 * Created by jtarraga on 03/08/16.
 */
public class VariantAvroSerializer extends AvroSerializer<VariantAvro> {

    private final int batchSize = 100;

    private String species = null;
    private String assembly = null;
    private String datasetName = null;

    public VariantAvroSerializer(String species, String assembly, String datasetName,
                                 String compression) {
        super(compression);
        this.species = species;
        this.assembly = assembly;
        this.datasetName = datasetName;
    }

    /**
     * Convert to Avro sequentially.
     *
     * @param inputFilename     VCF file name (input)
     * @param outputFilename    Avro file name (output)
     * @param annotator         Variant annotator
     * @throws IOException      Exception
     */
    public void toAvro(String inputFilename, String outputFilename, Object annotator) throws IOException {
        VariantAvroAnnotator variantAvroAnnotator = (VariantAvroAnnotator) annotator;

        File inputFile = new File(inputFilename);
        String filename = inputFile.getName();

        // VCF reader
        VcfFileReader vcfFileReader = new VcfFileReader(inputFilename, true);
        vcfFileReader.open();
        VCFHeader vcfHeader = vcfFileReader.getVcfHeader();

        // Avro writer
        OutputStream outputStream;
        if (StringUtils.isEmpty(outputFilename) || outputFilename.equals("STDOUT")) {
            outputStream = System.out;
        } else {
            outputStream = new FileOutputStream(outputFilename);
        }
        AvroFileWriter<VariantAvro> avroFileWriter = new AvroFileWriter<>(VariantAvro.SCHEMA$, compression, outputStream);
        avroFileWriter.open();
//        VariantGlobalStatsCalculator statsCalculator = new VariantGlobalStatsCalculator(vcfReader.getSource());
//        statsCalculator.pre();

        // Metadata management
        VariantMetadataManager metadataManager = new VariantMetadataManager();
        VariantStudyMetadata variantDatasetMetadata = new VariantStudyMetadata();
        variantDatasetMetadata.setId(datasetName);
        metadataManager.addVariantDatasetMetadata(variantDatasetMetadata);

        Cohort cohort = new Cohort("ALL", vcfHeader.getSampleNamesInOrder(), SampleSetType.MISCELLANEOUS);
        metadataManager.addCohort(cohort, variantDatasetMetadata.getId());

        // Add variant file metadata from VCF header
        metadataManager.addFile(filename, vcfHeader, variantDatasetMetadata.getId());
        metadataManager.getVariantMetadata().getStudies().get(0).setAggregatedHeader(
                metadataManager.getVariantMetadata().getStudies().get(0).getFiles().get(0).getHeader());

        // VariantContext-to-Variant converter
        VariantContextToVariantConverter converter = new VariantContextToVariantConverter(datasetName, filename,
                vcfHeader.getSampleNamesInOrder());

        // Main loop
        long i = 0, counter = 0;
        ConvertEncodeTask convertEncodeTask = new ConvertEncodeTask(converter, filters, variantAvroAnnotator);
        List<VariantContext> variantContexts = vcfFileReader.read(batchSize);
        while (variantContexts.size() > 0) {
            List<ByteBuffer> buffers = convertEncodeTask.apply(variantContexts);
            avroFileWriter.write(buffers);
            counter += buffers.size();
            if ((++i % 100) == 0) {
                System.out.println("\t... " + counter + " variants");
            }
            variantContexts = vcfFileReader.read(batchSize);
        }
        System.out.println("Number of processed records: " + counter);

        // Close
        vcfFileReader.close();
        avroFileWriter.close();
        outputStream.close();

        // Save metadata (JSON format)
        metadataManager.save(Paths.get(outputFilename + ".meta.json"), true);
    }

    /**
     * Convert to Avro using a given number of threads (run the parallel task runner engine).
     *
     * @param inputFilename     VCF file name (input)
     * @param outputFilename    Avro file name (output)
     * @param annotator         Variant annotator
     * @param numThreads        Number of threads
     * @throws IOException      Exception
     */
    public void toAvro(String inputFilename, String outputFilename, Object annotator, int numThreads)
            throws IOException {
        VariantAvroAnnotator variantAvroAnnotator = (VariantAvroAnnotator) annotator;

        File inputFile = new File(inputFilename);
        String filename = inputFile.getName();

        // Config parallel task runner
        ParallelTaskRunner.Config config = ParallelTaskRunner.Config.builder()
                .setNumTasks(numThreads)
                .setBatchSize(batchSize)
                .setSorted(true)
                .build();

        // VCF reader
        VcfFileReader vcfFileReader = new VcfFileReader(inputFilename, false);
        vcfFileReader.open();
        VCFHeader vcfHeader = vcfFileReader.getVcfHeader();

        // Avro writer
        OutputStream outputStream;
        if (StringUtils.isEmpty(outputFilename) || outputFilename.equals("STDOUT")) {
            outputStream = System.out;
        } else {
            outputStream = new FileOutputStream(outputFilename);
        }
        AvroFileWriter<VariantAvro> avroFileWriter = new AvroFileWriter<>(VariantAvro.SCHEMA$, compression, outputStream);

        // Metadata management
        VariantMetadataManager metadataManager = new VariantMetadataManager();
        VariantStudyMetadata variantDatasetMetadata = new VariantStudyMetadata();
        variantDatasetMetadata.setId(datasetName);
        metadataManager.addVariantDatasetMetadata(variantDatasetMetadata);

        Cohort cohort = new Cohort("ALL", vcfHeader.getSampleNamesInOrder(), SampleSetType.MISCELLANEOUS);
        metadataManager.addCohort(cohort, variantDatasetMetadata.getId());

        // Add variant file metadata from VCF header
        metadataManager.addFile(filename, vcfHeader, variantDatasetMetadata.getId());
        metadataManager.getVariantMetadata().getStudies().get(0).setAggregatedHeader(
                metadataManager.getVariantMetadata().getStudies().get(0).getFiles().get(0).getHeader());

        // VariantContext-to-Variant converter
        VariantContextToVariantConverter converter = new VariantContextToVariantConverter(datasetName,
                new File(inputFilename).getName(), vcfFileReader.getVcfHeader().getSampleNamesInOrder());

        // Create the parallel task runner
        ParallelTaskRunner<VariantContext, ByteBuffer> ptr;
        try {
            ConvertEncodeTask convertTask = new ConvertEncodeTask(converter, filters, variantAvroAnnotator);
            ptr = new ParallelTaskRunner(vcfFileReader, convertTask, avroFileWriter, config);
        } catch (Exception e) {
            throw new IOException("Error while creating ParallelTaskRunner", e);
        }
        try {
            ptr.run();
        } catch (ExecutionException e) {
            throw new IOException("Error while converting VCF to Avro in ParallelTaskRunner", e);
        }

        // Close
        vcfFileReader.close();
        avroFileWriter.close();
        outputStream.close();

        // Save metadata (JSON format)
        metadataManager.save(Paths.get(outputFilename + ".meta.json"), true);
    }

    /**
     * Add a region filter.
     *
     * @param region    Region to filter
     * @return          this (VariantAvroSerializer)
     */
    public VariantAvroSerializer addRegionFilter(Region region) {
        addFilter(v -> v.getChromosome().equals(region.getChromosome())
                && v.getEnd() >= region.getStart()
                && v.getStart() <= region.getEnd());
        return this;
    }

    /**
     * Add a list of region filters.
     *
     * @param regions   List of regions
     * @param and       AND boolean flag
     * @return          this (VariantAvroSerializer)
     */
    public VariantAvroSerializer addRegionFilter(List<Region> regions, boolean and) {
        List<Predicate<VariantAvro>> predicates = new ArrayList<>();
        regions.forEach(r -> predicates.add(v -> v.getChromosome().equals(r.getChromosome())
                && v.getEnd() >= r.getStart()
                && v.getStart() <= r.getEnd()));
        addFilter(predicates, and);
        return this;
    }

    /**
     * Add the valid ID filter.
     *
     * @return  this (VariantAvroSerializer)
     */
    public VariantAvroSerializer addValidIdFilter() {
        addFilter(v -> v.getId() != null && !v.getId().isEmpty() && !v.getId().equals("."));
        return this;
    }
}
