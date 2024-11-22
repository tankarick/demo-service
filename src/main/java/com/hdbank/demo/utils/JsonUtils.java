package com.hdbank.demo.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.jayway.jsonpath.*;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class JsonUtils {

    private Configuration conf;
    private ObjectMapper mapper;

    public void init() {
        mapper = JsonMapper.builder()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false)
                .configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true)
                .build();
        conf = Configuration.builder()
                .jsonProvider(new JacksonJsonProvider(mapper))
                .mappingProvider(new JacksonMappingProvider(mapper))
                .options(Option.SUPPRESS_EXCEPTIONS).build();
    }

    public String writeValueAsString(Object object) {
        try {
            if (object == null) {
                return null;
            }
            if (object instanceof String) {
                return ((String) object).replaceAll("\\s{2}", "");
            }
            return mapper.writeValueAsString(object).toString();
        } catch (Exception e) {
            log.error("Error when writeValueAsString {}", e.getMessage(), e);
            return null;
        }
    }

    public DocumentContext create() {
        return JsonPath.using(conf).parse("{}");
    }

    public DocumentContext toJson(String data) {
        try {
            if (data == null || data.isEmpty()) {
                return create();
            }
            return JsonPath.using(conf).parse(data.toString());
        } catch (Exception e) {
            log.error("Error when toJson {}", e.getMessage(), e);
            return null;
        }
    }

    public DocumentContext toJson(Object object) {
        try {
            if (object == null) {
                return create();
            }
            return JsonPath.using(conf).parse(writeValueAsString(object));
        } catch (Exception e) {
            log.error("Error when toJson {}", e.getMessage(), e);
            return null;
        }
    }

    public <T> T read(String data, String path, Class<T> type) {
        try {
            if (data == null || data.isEmpty()) {
                return null;
            }
            return JsonPath.using(conf).parse(data.toString()).read(path, type);
        } catch (Exception e) {
            log.error("Error when read {}", e.getMessage(), e);
            return null;
        }
    }

    public <T> T read(Object object, String path, Class<T> type) {
        try {
            if (object == null) {
                return null;
            }
            return JsonPath.using(conf).parse(writeValueAsString(object)).read(path, type);
        } catch (Exception e) {
            log.error("Error when read {}", e.getMessage(), e);
            return null;
        }
    }

    public <T> T read(String data, Class<T> type) {
        try {
            if (data == null || data.isEmpty()) {
                return null;
            }
            return JsonPath.using(conf).parse(data.toString()).read("$", type);
        } catch (Exception e) {
            log.error("Error when read {}", e.getMessage(), e);
            return null;
        }
    }

    public <T> T read(Object object, Class<T> type) {
        try {
            if (object == null) {
                return null;
            }
            return JsonPath.using(conf).parse(writeValueAsString(object)).read("$", type);
        } catch (Exception e) {
            log.error("Error when read {}", e.getMessage(), e);
            return null;
        }
    }

    public <T> T read(String data, TypeRef<T> type) {
        try {
            if (data == null || data.isEmpty()) {
                return null;
            }
            return JsonPath.using(conf).parse(data.toString()).read("$", type);
        } catch (Exception e) {
            log.error("Error when read {}", e.getMessage(), e);
            return null;
        }
    }

    public <T> T read(Object object, TypeRef<T> type) {
        try {
            if (object == null) {
                return null;
            }
            return JsonPath.using(conf).parse(writeValueAsString(object)).read("$", type);
        } catch (Exception e) {
            log.error("Error when read {}", e.getMessage(), e);
            return null;
        }
    }

    public <T> T read(String data, String path, TypeRef<T> type) {
        try {
            if (data == null || data.isEmpty()) {
                return null;
            }
            return JsonPath.using(conf).parse(data.toString()).read(path, type);
        } catch (Exception e) {
            log.error("Error when read {}", e.getMessage(), e);
            return null;
        }
    }

    public <T> T read(Object object, String path, TypeRef<T> type) {
        try {
            if (object == null) {
                return null;
            }
            return JsonPath.using(conf).parse(writeValueAsString(object)).read(path, type);
        } catch (Exception e) {
            log.error("Error when read {}", e.getMessage(), e);
            return null;
        }
    }
}
