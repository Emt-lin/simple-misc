package com.misc.core.proto.misc.serial;

import com.misc.core.exception.CodecException;
import com.misc.core.model.MiscPack;
import com.misc.core.proto.misc.common.MiscSerializableType;
import io.netty.buffer.ByteBuf;
import org.msgpack.MessagePack;

import java.io.IOException;

/**
 * @author psl
 * @date 2020/12/17
 */
public class MessagePackSerializableType implements MiscSerializableHandler{

    /**
     * 编解码器
     */
    private static final MessagePack pack = new MessagePack();

    /**
     * 编码器：长度+字节数组
     */
    @Override
    public void encode(MiscPack msg, ByteBuf out) throws CodecException {
        try {
            byte[] write = pack.write(msg);
            int length = write.length;
            out.writeInt(length);
            out.writeBytes(write);
        }catch (IOException e) {
            throw new CodecException(e);
        }
    }

    /**
     * 解码器：长度 字节数组
     */
    @Override
    public Object decode(ByteBuf in) throws CodecException {
        try {
            // 小于4 直接返回
            if(in.readableBytes() < 4) {
                return MiscSerializableType.NEED_MORE;
            }
            // 小于已读长度 返回
            int len = in.readInt();
            if(in.readableBytes() < len) {
                return MiscSerializableType.NEED_MORE;
            }
            byte[] body = new byte[len];
            in.readBytes(body, 0, len);
            return pack.read(body, MiscPack.class);
        }catch (IOException e) {
            throw new CodecException(e);
        }
    }

}
