package com.misc.core.serialization;

import com.misc.core.exception.SerializationException;

/**
 * 序列化
 */
public interface Serializer<V> {

    /**
     * V 是需要序列化的对象
     */
    byte[] serialize(V obj) throws SerializationException;
}
