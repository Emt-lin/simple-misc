package com.misc.core.proto.misc;

import com.misc.core.model.MiscPack;
import com.misc.core.proto.misc.common.MiscProperties;
import com.misc.core.proto.misc.common.MiscProtoConstance;
import com.misc.core.proto.misc.serial.MiscSerializableHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.HashMap;
import java.util.Map;

import static com.misc.core.commons.Constants.*;
import static com.misc.core.commons.PropertiesConstant.*;

/**
 * 编码器 将 {@link MiscPack} 编码成 ByteBuf，然后放入字节缓存区
 * <p>
 *     主要就是写 一个版本号 + 数据包长度 + 数据包, 来做校验
 * </p>
 *
 * ChannelOutboundHandlerAdapter --> MessageToByteEncoder --> MiscPackageEncoder
 */
public class MiscPackageEncoder extends MessageToByteEncoder<MiscPack> {

    /**
     * 自定义协议头
     */
    private final short version;

    /**
     * 写出类型
     */
    private final byte type;

    /**
     * 编解码处理器
     */
    private static final Map<Byte, MiscSerializableHandler> serializeHandlerMap = new HashMap<>();

    public MiscPackageEncoder(MiscProperties properties) {
        super();
        this.version = properties.getShort(CLIENT_SERVER_VERSION, DEFAULT_SERVER_VERSION);
        this.type = properties.getByte(CLIENT_SERIALIZABLE_TYPE, DEFAULT_SERIALIZABLE_TYPE.getCode());

        MiscSerializableHandler.initSerializeHandleMap(serializeHandlerMap);
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, MiscPack msg, ByteBuf out) throws Exception {
        // 魔数
        out.writeByte(MiscProtoConstance.MAGIC_NUMBER);
        // 服务版本号
        out.writeShort(version);
        // 序列号类型
        out.writeByte(type);

        MiscSerializableHandler miscSerializableHandler = serializeHandlerMap.getOrDefault(type,
                MiscSerializableHandler.DEFAULT_HANDLER);
        // 通过处理器，变成 MiscPack
        miscSerializableHandler.encode(msg, out);
    }
}
