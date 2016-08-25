package org.opencb.hpg.bigdata.core.io;


import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.opencb.commons.io.DataWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by mh719 on 11/05/2016.
 */
public class ProtoFileWriter<T extends com.google.protobuf.GeneratedMessage> implements DataWriter<T> {
    private String compression = StringUtils.EMPTY;
    protected Logger logger = LoggerFactory.getLogger(this.getClass().toString());

    private OutputStream outputStream = null;
    private Path input = null;
    private final AtomicLong timeWrite = new AtomicLong(0);

    public ProtoFileWriter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public ProtoFileWriter(Path input) {
        this.input = input;
    }

    public ProtoFileWriter(Path input, String compression) {
        this(input);
        this.compression = compression;
    }

    @Override
    public boolean open() {
        if (null != this.input) {
            try {
                OutputStream out = new BufferedOutputStream(new FileOutputStream(input.toFile()));
                if (StringUtils.equalsIgnoreCase(compression, "gzip") || StringUtils.equalsIgnoreCase(compression, "gz")) {
                    out = new GzipCompressorOutputStream(out);
                } else if (StringUtils.isNotBlank(compression)) {
                    throw new NotImplementedException("Proto compression not implemented yet: " + compression);
                }
                outputStream = out;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return true;
    }

    @Override
    public boolean close() {
        try {
            this.outputStream.flush();
        } catch (IOException e) {
            logger.error("Problems flushing outputstream", e);
            return false;
        }
        if (null != this.input) {
            try {
                this.outputStream.close();
            } catch (IOException e) {
                logger.error("Problems closing outputstream", e);
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean pre() {
        return false;
    }

    @Override
    public boolean post() {
        return false;
    }

    @Override
    public boolean write(T elem) {
        try {
            elem.writeDelimitedTo(this.outputStream);
        } catch (IOException e) {
            throw new IllegalStateException("Problems writing Proto element ot output stream!!! " + elem, e);
        }
        return true;
    }

    @Override
    public boolean write(List<T> batch) {
        batch.forEach(e -> write(e));
        return true;
    }
}
