package com.fasterxml.jackson.perf.msgpack;

import java.util.concurrent.TimeUnit;

import org.msgpack.jackson.dataformat.msgpack.MessagePackFactory;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.ReadPerfBaseFullJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Group) // Thread, Group or Benchmark
@OutputTimeUnit(TimeUnit.SECONDS)
public class MsgpackStdReadAfterburner
    extends ReadPerfBaseFullJackson<MediaItem>
{
    private final static MessagePackFactory _f = new MessagePackFactory();
    
    private static final ObjectMapper MAPPER = new ObjectMapper(_f);
    static {
        MAPPER.registerModule(new AfterburnerModule());
    }

    private final static InputConverter MSGPACKS = InputConverter.stdConverter(MAPPER);

    public MsgpackStdReadAfterburner() {
        super(MediaItem.class, MSGPACKS, MAPPER);
    }
}
