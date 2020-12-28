package com.misc.core.netty;

import com.misc.core.exception.ConvertException;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 这里处理协议
 * ProtoInBound 比如http协议，它的inbound是http request
 * ProtoOutBound 比如http协议，它的outbound是http response
 * <p>
 *     ChannelInBound -> 比如我们需要rpc request，此时需要将 http request 转换成rpc request，也就是解码器
 *     ChannelOutBound -> 这个就是编码器 encode，比如需要将rpc response 转换成 http request
 * </p>
 */
@ChannelHandler.Sharable
public abstract class NettyConvertHandler<ProtoInBound, ProtoOutBound, ChannelInBound, ChannelOutBound>
        extends ChannelDuplexHandler {

    protected static final Logger logger = LoggerFactory.getLogger(NettyConvertHandler.class);

    /**
     * 主要是用于，比如HTTP拿到请求，然后需要转换成 RPC REQUEST
     */
    protected abstract ChannelInBound decode(ProtoInBound msg) throws ConvertException;

    /**
     * 主要是用于编码，将RPC response 转换成 HTTP response
     */
    protected abstract ProtoOutBound encode(ByteBufAllocator allocator, ChannelOutBound msg) throws ConvertException;

    /**
     * 写出去的是请求，异常需要自己抓取，promise
     */
    @Override
    @SuppressWarnings("all")
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        super.write(ctx, encode(ctx.alloc(), (ChannelOutBound)msg), promise);
    }

    /**
     * 编码 -> misc-request
     */
    @Override
    @SuppressWarnings("all")
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, decode((ProtoInBound) msg));
    }
}
