package com.misc.core.proto.misc;

import com.misc.core.proto.misc.common.MiscProperties;
import com.misc.core.proto.misc.common.MiscSerializableType;
import com.misc.core.proto.misc.serial.MiscSerializableHandler;
import io.netty.buffer.ByteBuf;
import com.misc.core.commons.Constants;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.misc.core.commons.Constants.DEFAULT_SERVER_VERSION;
import static com.misc.core.commons.PropertiesConstant.*;
import static com.misc.core.proto.misc.common.MiscProtoConstance.MAGIC_NUMBER;

/**
 * 解码器 会很麻烦
 * <p>
 *     主要分4中情况：
 *     1. 缓冲区只有一个数据包，此时只用做 版本校验、长度校验，然后读就可以了
 *     2. 缓冲区有多个数据包，可能是整数的倍数，就需要迭代读取
 *     3. 缓冲区可能有多个数据包，可能出现半个包的问题，比如：2.5个包，此时就需要解码注意
 *     4. 如果出现半个+整数个, 前面根本无法解码，此时就无法处理，可能出现丢包
 *
 *     所以我们要求的是数据传输的完成性，最低要求将数据包完整的传输和接收
 * </p>
 */
public class MiscPackageDecoder extends ByteToMessageDecoder {

    /**
     * 默认值是 {@link Constants#DEFAULT_SERVER_VERSION}
     */
    private final short serverVersion;

    /**
     * 魔数(1) + 版本号(2) + 类型(1)
     */
    private static final int VERSION_AND_TYPE_LEN = 4;

    /**
     * 编解码处理器
     */
    private static final Map<Byte, MiscSerializableHandler> serializeHandlerMap = new HashMap<>();

    /**
     * 构造方法
     */
    public MiscPackageDecoder(MiscProperties properties) {
        super();
        this.serverVersion = properties.getShort(CLIENT_SERVER_VERSION, DEFAULT_SERVER_VERSION);

        MiscSerializableHandler.initSerializeHandleMap(serializeHandlerMap);
    }

    /**
     * {@link ByteToMessageDecoder#channelRead(ChannelHandlerContext, Object)}
     *
     * 解码器
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // version+type+magic 等于4
        // 这里应该写成if吗？
        while (in.readableBytes() > VERSION_AND_TYPE_LEN) {


             /* // 防止socket字节流攻击
            if (in.readableBytes() > 2048) {
                in.skipBytes(in.readableBytes());
            }

            // 记录包头开始的index
            int beginReader;

            // 循环读，一直读到包头为止。因为需要跳过，不需要的字节。（比如：被socket攻击的字节流）
            while (true) {

                beginReader = in.readerIndex();

                // 一定要读到包头
                if(in.readInt() == MAGIC_NUMBER) break;

                // 如果没有读到包头，就只跳过一个字节。防止出现把包头被拆分读，而找不到包头
                in.readerIndex(beginReader);
                in.readByte();

                // 如果可读的数据长度 小于基本长度，直接返回
                if(in.readableBytes() < VERSION_AND_TYPE_LEN) {
                    return;
                }
            }*/

            // 保存点
            int save = in.readerIndex();

            // 判断魔数
            byte magic = in.readByte();
            if(magic != MAGIC_NUMBER) {
                in.readerIndex(save);
                return;
            }

            // 获取版本号
            short version = in.readShort();
            if(version != serverVersion) {
                in.readerIndex(save);
                return;
            }
            // 编辑码类型
            byte type = in.readByte();
            MiscSerializableHandler handler = serializeHandlerMap.getOrDefault(type, MiscSerializableHandler.DEFAULT_HANDLER);
            Object res = handler.decode(in);
            // 需要跟多则返回继续读
            if(res == MiscSerializableType.NEED_MORE) {
                in.readerIndex(save);
                return;
            }
            // 添加进去
            out.add(res);
        }
    }
}
