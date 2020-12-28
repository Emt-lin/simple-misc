package com.misc.core.proto.misc.serial;

import com.misc.core.exception.CodecException;
import com.misc.core.model.MiscPack;
import com.misc.core.proto.misc.common.MiscSerializableType;
import com.misc.core.util.FileUtil;
import io.netty.buffer.ByteBuf;
import org.msgpack.MessagePack;

import java.io.IOException;

/**
 * @author psl
 * @date 2020/12/17
 */
public class GzipMessagePackSerializableType implements MiscSerializableHandler{

    private static final MessagePack pack = new MessagePack();

    @Override
    public void encode(MiscPack msg, ByteBuf out) throws CodecException {
        try {
            // 序列化
            byte[] body = pack.write(msg);
            // 压缩
            byte[] gzip = FileUtil.gzip(body);
            // 先写长度
            out.writeInt(gzip.length);
            out.writeBytes(gzip);
        }catch (IOException e) {
            throw new CodecException(e);
        }
    }

    @Override
    public Object decode(ByteBuf in) throws CodecException {
        // 小于长度4，直接返回。因为长度是4字节
        if(in.readableBytes() < 4) {
            return MiscSerializableType.NEED_MORE;
        }

        // 小于已读长度，返回
        int len = in.readInt();
        if(in.readableBytes() < len) {
            return MiscSerializableType.NEED_MORE;
        }
        byte[] body = new byte[len];
        in.readBytes(body, 0, len);
        try {
            byte[] bytes = FileUtil.unGzip(body);
            return pack.read(bytes, MiscPack.class);
        }catch (IOException e) {
            throw new CodecException(e);
        }
    }

}
