package com.hdbank.demo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdbank.demo.exception.DemoException;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JsonService {

    @Autowired
    private ObjectMapper objectMapper;

    public String writeValueAsString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new DemoException(e);
        }
    }

    public DocumentContext toDocumentContext(Object object) {
        try {
            Configuration conf = Configuration.builder().options(Option.DEFAULT_PATH_LEAF_TO_NULL).build();
            return JsonPath.using(conf).parse(object);
        } catch (Exception e) {
            throw new DemoException(e);
        }
    }

    public <T> T read(Object object, String path, Class<T> type) {
        try {
            Configuration conf = Configuration.builder().options(Option.DEFAULT_PATH_LEAF_TO_NULL).build();
            return JsonPath.using(conf).parse(object).read(path, type);
        } catch (Exception e) {
            throw new DemoException(e);
        }
    }

    public <T> T read(Object object, Class<T> type) {
        try {
            Configuration conf = Configuration.builder().options(Option.DEFAULT_PATH_LEAF_TO_NULL).build();
            return JsonPath.using(conf).parse(object.toString()).read("$", type);
        } catch (Exception e) {
            throw new DemoException(e);
        }
    }
}
