/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package org.opencb.hpg.bigdata.app.cli.local.avro;

import java.io.Serializable;

@SuppressWarnings("all")
public class VariantAvro implements Serializable {
  /** * The variant ID. */
   private String id;
  /** * Other names used for this genomic variation. */
   private java.util.List<String> names;
  /** * Chromosome where the genomic variation occurred. */
   private String chromosome;
  /** * Normalized position where the genomic variation starts.
         * <ul>
         * <li>SNVs have the same start and end position</li>
         * <li>Insertions start in the last present position: if the first nucleotide
         * is inserted in position 6, the start is position 5</li>
         * <li>Deletions start in the first previously present position: if the first
         * deleted nucleotide is in position 6, the start is position 6</li>
         * </ul> */
   private int start;
  /** * Normalized position where the genomic variation ends.
         * <ul>
         * <li>SNVs have the same start and end positions</li>
         * <li>Insertions end in the first present position: if the last nucleotide
         * is inserted in position 9, the end is position 10</li>
         * <li>Deletions ends in the last previously present position: if the last
         * deleted nucleotide is in position 9, the end is position 9</li>
         * </ul> */
   private int end;
  /** * Reference allele. */
   private String reference;
  /** * Alternate allele. */
   private String alternate;
  /** * Reference strand for this variant */
   private String strand;
  /** * Information regarding Structural Variants */
   private StructuralVariation sv;
  /** * Length of the genomic variation, which depends on the variation type.
         * <ul>
         * <li>SNVs have a length of 1 nucleotide</li>
         * <li>Indels have the length of the largest allele</li>
         * </ul> */
   private int length;
  /** * Type of variation: single nucleotide, indel or structural variation. */
   private String type;
  /** * Unique identifier following the HGVS nomenclature. */
   private java.util.Map<String,java.util.List<String>> hgvs;
  /** * Information specific to each study the variant was read from, such as
         * samples or statistics. */
   private java.util.List<StudyEntry> studies;
  /** * Annotations of the genomic variation. */
   private VariantAnnotation annotation;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public VariantAvro() {}

  /**
   * All-args constructor.
   */
  public VariantAvro(String id, java.util.List<String> names, String chromosome, Integer start, Integer end, String reference, String alternate, String strand, StructuralVariation sv, Integer length, String type, java.util.Map<String,java.util.List<String>> hgvs, java.util.List<StudyEntry> studies, VariantAnnotation annotation) {
    this.id = id;
    this.names = names;
    this.chromosome = chromosome;
    this.start = start;
    this.end = end;
    this.reference = reference;
    this.alternate = alternate;
    this.strand = strand;
    this.sv = sv;
    this.length = length;
    this.type = type;
    this.hgvs = hgvs;
    this.studies = studies;
    this.annotation = annotation;
  }

  /**
   * Gets the value of the 'id' field.
   * * The variant ID.   */
  public String getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * * The variant ID.   * @param value the value to set.
   */
  public void setId(String value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'names' field.
   * * Other names used for this genomic variation.   */
  public java.util.List<String> getNames() {
    return names;
  }

  /**
   * Sets the value of the 'names' field.
   * * Other names used for this genomic variation.   * @param value the value to set.
   */
  public void setNames(java.util.List<String> value) {
    this.names = value;
  }

  /**
   * Gets the value of the 'chromosome' field.
   * * Chromosome where the genomic variation occurred.   */
  public String getChromosome() {
    return chromosome;
  }

  /**
   * Sets the value of the 'chromosome' field.
   * * Chromosome where the genomic variation occurred.   * @param value the value to set.
   */
  public void setChromosome(String value) {
    this.chromosome = value;
  }

  /**
   * Gets the value of the 'start' field.
   * * Normalized position where the genomic variation starts.
         * <ul>
         * <li>SNVs have the same start and end position</li>
         * <li>Insertions start in the last present position: if the first nucleotide
         * is inserted in position 6, the start is position 5</li>
         * <li>Deletions start in the first previously present position: if the first
         * deleted nucleotide is in position 6, the start is position 6</li>
         * </ul>   */
  public Integer getStart() {
    return start;
  }

  /**
   * Sets the value of the 'start' field.
   * * Normalized position where the genomic variation starts.
         * <ul>
         * <li>SNVs have the same start and end position</li>
         * <li>Insertions start in the last present position: if the first nucleotide
         * is inserted in position 6, the start is position 5</li>
         * <li>Deletions start in the first previously present position: if the first
         * deleted nucleotide is in position 6, the start is position 6</li>
         * </ul>   * @param value the value to set.
   */
  public void setStart(Integer value) {
    this.start = value;
  }

  /**
   * Gets the value of the 'end' field.
   * * Normalized position where the genomic variation ends.
         * <ul>
         * <li>SNVs have the same start and end positions</li>
         * <li>Insertions end in the first present position: if the last nucleotide
         * is inserted in position 9, the end is position 10</li>
         * <li>Deletions ends in the last previously present position: if the last
         * deleted nucleotide is in position 9, the end is position 9</li>
         * </ul>   */
  public Integer getEnd() {
    return end;
  }

  /**
   * Sets the value of the 'end' field.
   * * Normalized position where the genomic variation ends.
         * <ul>
         * <li>SNVs have the same start and end positions</li>
         * <li>Insertions end in the first present position: if the last nucleotide
         * is inserted in position 9, the end is position 10</li>
         * <li>Deletions ends in the last previously present position: if the last
         * deleted nucleotide is in position 9, the end is position 9</li>
         * </ul>   * @param value the value to set.
   */
  public void setEnd(Integer value) {
    this.end = value;
  }

  /**
   * Gets the value of the 'reference' field.
   * * Reference allele.   */
  public String getReference() {
    return reference;
  }

  /**
   * Sets the value of the 'reference' field.
   * * Reference allele.   * @param value the value to set.
   */
  public void setReference(String value) {
    this.reference = value;
  }

  /**
   * Gets the value of the 'alternate' field.
   * * Alternate allele.   */
  public String getAlternate() {
    return alternate;
  }

  /**
   * Sets the value of the 'alternate' field.
   * * Alternate allele.   * @param value the value to set.
   */
  public void setAlternate(String value) {
    this.alternate = value;
  }

  /**
   * Gets the value of the 'strand' field.
   * * Reference strand for this variant   */
  public String getStrand() {
    return strand;
  }

  /**
   * Sets the value of the 'strand' field.
   * * Reference strand for this variant   * @param value the value to set.
   */
  public void setStrand(String value) {
    this.strand = value;
  }

  /**
   * Gets the value of the 'sv' field.
   * * Information regarding Structural Variants   */
  public StructuralVariation getSv() {
    return sv;
  }

  /**
   * Sets the value of the 'sv' field.
   * * Information regarding Structural Variants   * @param value the value to set.
   */
  public void setSv(StructuralVariation value) {
    this.sv = value;
  }

  /**
   * Gets the value of the 'length' field.
   * * Length of the genomic variation, which depends on the variation type.
         * <ul>
         * <li>SNVs have a length of 1 nucleotide</li>
         * <li>Indels have the length of the largest allele</li>
         * </ul>   */
  public Integer getLength() {
    return length;
  }

  /**
   * Sets the value of the 'length' field.
   * * Length of the genomic variation, which depends on the variation type.
         * <ul>
         * <li>SNVs have a length of 1 nucleotide</li>
         * <li>Indels have the length of the largest allele</li>
         * </ul>   * @param value the value to set.
   */
  public void setLength(Integer value) {
    this.length = value;
  }

  /**
   * Gets the value of the 'type' field.
   * * Type of variation: single nucleotide, indel or structural variation.   */
  public String getType() {
    return type;
  }

  /**
   * Sets the value of the 'type' field.
   * * Type of variation: single nucleotide, indel or structural variation.   * @param value the value to set.
   */
  public void setType(String value) {
    this.type = value;
  }

  /**
   * Gets the value of the 'hgvs' field.
   * * Unique identifier following the HGVS nomenclature.   */
  public java.util.Map<String,java.util.List<String>> getHgvs() {
    return hgvs;
  }

  /**
   * Sets the value of the 'hgvs' field.
   * * Unique identifier following the HGVS nomenclature.   * @param value the value to set.
   */
  public void setHgvs(java.util.Map<String,java.util.List<String>> value) {
    this.hgvs = value;
  }

  /**
   * Gets the value of the 'studies' field.
   * * Information specific to each study the variant was read from, such as
         * samples or statistics.   */
  public java.util.List<StudyEntry> getStudies() {
    return studies;
  }

  /**
   * Sets the value of the 'studies' field.
   * * Information specific to each study the variant was read from, such as
         * samples or statistics.   * @param value the value to set.
   */
  public void setStudies(java.util.List<StudyEntry> value) {
    this.studies = value;
  }

  /**
   * Gets the value of the 'annotation' field.
   * * Annotations of the genomic variation.   */
  public VariantAnnotation getAnnotation() {
    return annotation;
  }

  /**
   * Sets the value of the 'annotation' field.
   * * Annotations of the genomic variation.   * @param value the value to set.
   */
  public void setAnnotation(VariantAnnotation value) {
    this.annotation = value;
  }
}