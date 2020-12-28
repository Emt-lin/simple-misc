package com.misc.core.proto.http;

import com.misc.core.netty.NettyCodecProvider;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

/**
 * misc 协议的 解码器
 */
public class HttpCodecProvider implements NettyCodecProvider<FullHttpRequest, FullHttpResponse> {

    public HttpCodecProvider() {

    }
    @Override
    public ChannelHandler[] get() {
        return null;
    }
}
