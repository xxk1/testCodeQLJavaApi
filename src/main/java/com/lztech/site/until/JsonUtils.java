package com.lztech.site.until;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Json转化工具类
 */
public class JsonUtils {

    private static ObjectMapper mapper;

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 集合泛型
     * @param elementClasses  对象泛型
     * @return JSON字符串转换类型
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        mapper = new ObjectMapper();
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * json转List<Map>
     *
     * @param json json字符串
     * @return List<Map>
     * @throws IOException
     */
    public static List<Map<String, Object>> getListMap(String json) throws IOException {
        mapper = new ObjectMapper();
        JavaType javaType = getCollectionType(List.class, Map.class);
        return mapper.readValue(json, javaType);
    }

    /**
     * json转map
     *
     * @param json json字符串
     * @return map
     * @throws IOException
     */
    public static Map<String, Object> getMap(String json) throws IOException {
        mapper = new ObjectMapper();
        return mapper.readValue(json, Map.class);
    }

    /**
     * 对象转json
     *
     * @param collectionClass 集合泛型
     * @return json字符串
     * @throws JsonProcessingException
     */
    public static String getJson(Object collectionClass) throws JsonProcessingException {
        mapper = new ObjectMapper();
        return mapper.writeValueAsString(collectionClass);
    }

    public static List<String> getList(String json) throws IOException {
        mapper = new ObjectMapper();
        JavaType javaType = getCollectionType(List.class, String.class);
        return mapper.readValue(json, javaType);
    }
}
