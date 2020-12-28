package com.misc.core.proto.misc.serial;

import com.misc.core.exception.CodecException;
import com.misc.core.model.MiscPack;
import com.misc.core.proto.misc.common.MiscSerializableType;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

/**
 * 直接使用基本类型进行转换..
 */
public class ByteSerializableType implements MiscSerializableHandler {

    @Override
    public void encode(MiscPack msg, ByteBuf out) throws CodecException {
        // url
        byte[] url = null;

        int url_len = 0;
        if(msg.getRouter() != null) {
            url_len = msg.getRouter().length();
            url = msg.getRouter().getBytes();
        }

        int body_len = 0;
        if(msg.getBody() != null) {
            body_len = msg.getBody().length;
        }

        out.writeInt(url_len);
        out.writeInt(body_len);
        if(url != null) {
            out.writeBytes(url);
        }
        if(msg.getBody() != null) {
            out.writeBytes(msg.getBody());
        }
        out.writeLong(msg.getTimestamp());
    }

    @Override
    public Object decode(ByteBuf in) throws CodecException {
        // 长度小于 8直接返回. 因为：url的长度+body的长度
        if(in.readableBytes() < 8) return MiscSerializableType.NEED_MORE;

        int url_len = in.readInt();
        int body_len = in.readInt();

        // 这里的8代表时间戳
        if(in.readableBytes() < url_len + body_len + 8) return MiscSerializableType.NEED_MORE;

        byte[] url = new byte[url_len];
        byte[] body = new byte[body_len];
        in.readBytes(url, 0, url_len);
        in.readBytes(body, 0, body_len);
        long time = in.readLong();

        return new MiscPack(new String(url), body, time);

    }

}
