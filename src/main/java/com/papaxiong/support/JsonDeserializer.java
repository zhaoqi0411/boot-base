package com.papaxiong.support;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

import java.lang.reflect.Type;

public class JsonDeserializer  implements ObjectDeserializer {
    @Override
    public String deserialze(DefaultJSONParser defaultJSONParser, Type type, Object o) {
        final String input = defaultJSONParser.lexer.stringVal();
        if(type== String.class&& input.trim().equalsIgnoreCase("")){
            return null;
        }
        return input;
    }

    @Override
    public int getFastMatchToken() {
        return JSONToken.LITERAL_STRING;
    }
}
