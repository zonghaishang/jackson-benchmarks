package com.fasterxml.jackson.perf.xml;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.*;
import com.fasterxml.jackson.perf.data.MinimalInputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class XMLStdReadAfterburner
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = StaxProvider.xmlMapper();
    static {
        MAPPER.registerModule(new AfterburnerModule());
    }

    private final static MinimalInputConverter XML = MinimalInputConverter.minimalConverter(MAPPER);

    public XMLStdReadAfterburner() {
        super(MediaItem.class, XML, MAPPER);
    }
}
