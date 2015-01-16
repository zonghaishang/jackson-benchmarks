package com.fasterxml.jackson.perf.data;

import java.io.IOException;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.perf.model.MediaItems;

public class MinimalInputConverter
{
    protected final byte[] _mediaItemBytes;

    protected MinimalInputConverter(byte[] mib)
    {
        _mediaItemBytes = mib;
    }

    public static MinimalInputConverter minimalConverter(ObjectMapper targetMapper) {
        return minimalConverter(targetMapper, null);
    }

    public static MinimalInputConverter minimalConverter(ObjectMapper targetMapper,
    		FormatSchema schema)
    {
        return minimalConverter(targetMapper, schema, MediaItems.stdMediaItem());
    }

    public static MinimalInputConverter minimalConverter(ObjectMapper targetMapper,
            FormatSchema schema, Object value)
      {
          ObjectWriter w = targetMapper.writer();
          if (schema != null) {
              w = w.with(schema);
          }
          try {
               return new MinimalInputConverter(w.writeValueAsBytes(value));
          } catch (IOException e) {
              throw new RuntimeException(e);
          }
      }
    
    public byte[] bytesForMediaItem() {
        return _mediaItemBytes;
    }
}
