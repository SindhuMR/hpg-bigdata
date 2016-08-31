/*
 * Copyright 2015 OpenCB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opencb.hpg.bigdata.core.avro;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by jtarraga on 03/08/16.
 */
public abstract class AvroSerializer<T> {

    protected String compression;

    protected List<Predicate<T>> filters;

    public AvroSerializer() {
        this("deflate");
    }

    public AvroSerializer(String compression) {
        this.compression = compression;

        filters = new ArrayList<>();
    }

    public boolean filter(T record) {
        for (Predicate filter: filters) {
            if (!filter.test(record)) {
                return false;
            }
        }
        return true;
    }

    public AvroSerializer addFilter(Predicate<T> predicate) {
        getFilters().add(predicate);
        return this;
    }

    public void toAvro(String inputFilename, String outputFilename) throws IOException {
        InputStream inputStream = new FileInputStream(inputFilename);
        toAvro(inputStream, outputFilename);
        inputStream.close();
    }

    public abstract void toAvro(InputStream inputStream, String outputFilename) throws IOException;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AvroSerializer{");
        sb.append("compression=").append(compression);
        sb.append(", filters=").append(filters);
        sb.append('}');
        return sb.toString();
    }

    public List<Predicate<T>> getFilters() {
        return filters;
    }

    public AvroSerializer setFilters(List<Predicate<T>> filters) {
        this.filters = filters;
        return this;
    }

    public String getCompression() {
        return compression;
    }

    public void setCompression(String compression) {
        this.compression = compression;
    }
}
