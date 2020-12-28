package com.misc.core.serialization;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.misc.core.exception.SerializationException;

import java.lang.reflect.Type;

/**
 * todo
 */
public class JsonObjectArraySerialization implements Deserializer<Object[], Class<?>[]>,
        Serializer<Object[]> {

    /**
     * 序列化类型
     */
    private static final Type TYPE = new TypeReference<Object[]>(){
    }.getType();

    // 返回 反序列化的结果
    @Override
    public Object[] deserialize(byte[] arr, Class<?>[] types) throws SerializationException {
        if(arr == null || arr.length == 0) return null;
        return JSONArray.parseArray(new String(arr), types).toArray();
    }

    // 返回 序列化的结果
    @Override
    public byte[] serialize(Object[] obj) throws SerializationException {
        if(obj == null || obj.length == 0) {
            return null;
        }
        return JSON.toJSONBytes(obj);
    }
}
