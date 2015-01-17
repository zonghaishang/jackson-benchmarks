package com.fasterxml.jackson.perf.csv;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.FlattenedMediaItem;
import com.fasterxml.jackson.perf.model.MediaItems;

@State(Scope.Group) // Thread, Group or Benchmark
public class CsvStdWriteAfterburner
    extends WritePerfBasicJackson<FlattenedMediaItem>
{
    private static final CsvMapper MAPPER = new CsvMapper();
    static {
        MAPPER.registerModule(new AfterburnerModule());
    }

    private final static CsvSchema _mediaItemSchema;
    static {
        _mediaItemSchema = MAPPER.typedSchemaFor(FlattenedMediaItem.class);
    }

    public CsvStdWriteAfterburner() {
        super(MAPPER, _mediaItemSchema, MediaItems.flatMediaItem());
    }
}