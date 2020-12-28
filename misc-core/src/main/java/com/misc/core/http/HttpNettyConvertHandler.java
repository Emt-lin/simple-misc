package com.misc.core.http;

import com.misc.core.exception.ConvertException;
import com.misc.core.netty.NettyConvertHandler;
import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

/**
 * http的转换器
 */
public class HttpNettyConvertHandler<ChannelInBound, ChannelOutBound> extends
        NettyConvertHandler<FullHttpRequest, FullHttpResponse, ChannelInBound, ChannelOutBound> {

    @Override
    protected ChannelInBound decode(FullHttpRequest msg) throws ConvertException {
        return null;
    }

    @Override
    protected FullHttpResponse encode(ByteBufAllocator allocator, ChannelOutBound msg) throws ConvertException {
        return null;
    }
}
