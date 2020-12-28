package com.misc.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * JSON 工具
 */
public final class JsonUtil {

    public static <T> T parseObject(String json, TypeReference<T> type) {
        return JSON.parseObject(json, type);
    }

    public static String toJSONString(Object object) {
        return JSON.toJSONString(object);
    }
}
