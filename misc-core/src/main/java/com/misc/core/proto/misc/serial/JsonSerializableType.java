package com.misc.core.proto.misc.serial;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.misc.core.exception.CodecException;
import com.misc.core.model.MiscPack;
import com.misc.core.proto.misc.common.MiscSerializableType;
import io.netty.buffer.ByteBuf;

import java.lang.reflect.Type;

/**
 * JSON 序列化
 */
public class JsonSerializableType implements MiscSerializableHandler{

    /**
     * 序列号类型
     */
    private static final Type TYPE = new TypeReference<MiscPack>() {
    }.getType();

    @Override
    public void encode(MiscPack msg, ByteBuf out) throws CodecException {
        byte[] body = JSON.toJSONBytes(msg);
        out.writeInt(body.length);
        out.writeBytes(body);
    }

    @Override
    public Object decode(ByteBuf in) throws CodecException {
        // 小于4直接返回
        if(in.readableBytes() < 4) {
            return MiscSerializableType.NEED_MORE;
        }
        // 小于数据长度，返回
        int len = in.readInt();
        if(in.readableBytes() < len) {
            return MiscSerializableType.NEED_MORE;
        }
        byte[] body = new byte[len];
        in.readBytes(body, 0, len);
        return JSON.parseObject(body, TYPE);
    }

}
