package com.iiplabs.spg.web.utils.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.iiplabs.spg.web.utils.StringUtil;

public class PanMaskSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        String serializedOutput = "";
        if (value != null) {
            serializedOutput = StringUtil.maskString(value, START, END, MASK_CHAR);
        }
        gen.writeString(serializedOutput);
    }

    private static final char MASK_CHAR = '*';
    private static final int START = 0;
    private static final int END = 12;

}
