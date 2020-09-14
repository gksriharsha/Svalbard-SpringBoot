package com.project.Svalbard.Util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.project.Svalbard.Model.db.Classification;

import java.util.HashMap;
import java.util.Map;

public class Mapper {
    public static Map<String, Object> getfromObject(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return (Map<String, Object>) mapper.convertValue(obj, Map.class);
    }

    public static Classification getasClassification(HashMap<String, Object> hashMap) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(hashMap, Classification.class);
    }
}
