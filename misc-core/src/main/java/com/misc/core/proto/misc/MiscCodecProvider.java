package com.misc.core.proto.misc;

import com.misc.core.model.MiscPack;
import com.misc.core.netty.NettyCodecProvider;
import com.misc.core.proto.misc.common.MiscProperties;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * misc 协议的 解码器
 */
public class MiscCodecProvider implements NettyCodecProvider<MiscPack, MiscPack> {

    private final MiscProperties properties;

    public MiscCodecProvider(MiscProperties properties) {
        this.properties = properties == null ? new MiscProperties() : properties;
    }

    @Override
    public ChannelHandler[] get() {
        return new ChannelHandler[] {
                new ChannelDuplexHandler() {
                    @Override
                    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                        System.out.println("----------" + cause.getMessage());
                    }
                },
                new MiscPackageEncoder(properties),
                new MiscPackageDecoder(properties)
        };
    }
}
