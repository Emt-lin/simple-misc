package com.misc.core.util;

import com.misc.core.commons.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * todo
 */
public class ReflectUtil {

    private static final Map<String, Class<?>> defaultClass = new HashMap<>();

    static {
        defaultClass.put(byte.class.getName(), byte.class);
        defaultClass.put(short.class.getName(), short.class);
        defaultClass.put(int.class.getName(), int.class);
        defaultClass.put(long.class.getName(), long.class);
        defaultClass.put(char.class.getName(), char.class);
        defaultClass.put(boolean.class.getName(), boolean.class);
        defaultClass.put(double.class.getName(), double.class);
        defaultClass.put(float.class.getName(), float.class);

        defaultClass.put(byte[].class.getName(), byte[].class);
        defaultClass.put(short[].class.getName(), short[].class);
        defaultClass.put(int[].class.getName(), int[].class);
        defaultClass.put(long[].class.getName(), long[].class);
        defaultClass.put(char[].class.getName(), char[].class);
        defaultClass.put(boolean[].class.getName(), boolean[].class);
        defaultClass.put(double[].class.getName(), double[].class);
        defaultClass.put(float[].class.getName(), float[].class);
    }

    private static final LRUCache<String, Class<?>> methodTypesMap = new LRUCache<>(Constants.DEFAULT_LRU_CACHE_SIZE);

    private static final LRUCache<String, Class<?>> returnTypeCache = new LRUCache<>(Constants.DEFAULT_LRU_CACHE_SIZE);

    public static Class<?> classForName (String className) throws ClassNotFoundException {
        Class<?> returnClass = returnTypeCache.get(className);
        if (returnClass == null) {
            Class<?> target = null;
            try {
                target = Class.forName(className);
            }catch (ClassNotFoundException e) {
                target = defaultClass.get(className);
                if (target == null) {
                    throw e;
                }
            }
            // 放入LRUCache
            returnTypeCache.put(className, target);
            return returnTypeCache.get(className);
        }
        return returnClass;
    }
}
