package com.misc.core.serialization;

import com.misc.core.exception.SerializationException;

/**
 * 反序列化
 */
public interface Deserializer<R, T> {

    /**
     * T 是告诉我们想反序列化成什么类型的
     * R 是结果
     */
    R deserialize(byte[] arr, T type) throws SerializationException;
}
