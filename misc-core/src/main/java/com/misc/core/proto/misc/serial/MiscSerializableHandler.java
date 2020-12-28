package com.misc.core.proto.misc.serial;

import com.misc.core.exception.CodecException;
import com.misc.core.model.MiscPack;
import com.misc.core.proto.misc.common.MiscSerializableType;
import com.misc.core.util.ExceptionUtils;
import io.netty.buffer.ByteBuf;

import java.util.Map;

/**
 *
 * 解码处理器
 */
public interface MiscSerializableHandler {
    /**
     * 解码，如果需要更多字节数，返回{@link com.misc.core.proto.misc.common.MiscSerializableType#NEED_MORE}
     * 解码器：长度 字节数组
     */
    Object decode(ByteBuf in) throws CodecException;

    /**
     * 编码，严格控制长度
     * 编码器：长度+字节数组
     */
    void encode(MiscPack msg, ByteBuf out) throws CodecException;

    MiscSerializableHandler DEFAULT_HANDLER = new MiscSerializableHandler() {
        @Override
        public Object decode(ByteBuf in) throws CodecException {
            throw new RuntimeException("MiscSerializableHandler 无法序列化");
        }

        @Override
        public void encode(MiscPack msg, ByteBuf out) throws CodecException {
            throw new RuntimeException("MiscSerializableHandler 无法序列化");
        }
    };

    static void initSerializeHandleMap(Map<Byte, MiscSerializableHandler> codecHandlerMap) {
        if(codecHandlerMap == null) {
            throw ExceptionUtils.newNullPointerException("Map<Byte MiscSerializableHandler> 为空");
        }

        codecHandlerMap.put(MiscSerializableType.MESSAGE_PACK.getCode(), new MessagePackSerializableType());
        codecHandlerMap.put(MiscSerializableType.MESSAGE_PACK_GZIP.getCode(), new GzipMessagePackSerializableType());
    }
}
