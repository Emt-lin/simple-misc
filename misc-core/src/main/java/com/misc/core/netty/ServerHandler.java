package com.misc.core.netty;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;

/**
 * 核心处理器
 * 为啥要泛型，是因为为了高度的扩展
 */
@ChannelHandler.Sharable
public class ServerHandler<INBOUND, OUTBOUND> extends ChannelDuplexHandler {
    private static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    /**
     * 线程池
     */
    private Executor executor;

    /**
     * 处理器
     */
    private NettyEventListener<INBOUND, OUTBOUND> nettyEventListener;

    /**
     * 构造器
     */
    ServerHandler(Executor executor, NettyEventListener<INBOUND, OUTBOUND> nettyEventListener){
        this.executor = executor;
        this.nettyEventListener = nettyEventListener;
    }

    /**
     * 接收， 不向下传递
     */
    @SuppressWarnings("all")
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            // 乳沟我们没有线程池（客户端一般没有）
            if(executor == null) {
                logger.debug("[MISC] received {}", msg);
                nettyEventListener.received(ctx.channel(), (INBOUND) msg);
            }else {
                // 如果有
                executor.execute(() -> {
                    try {
                        logger.debug("[MISC] received {}", msg);
                        nettyEventListener.received(ctx.channel(), (INBOUND)msg);
                    }catch (Throwable e) {
                        // 发送异常传递
                        nettyEventListener.caught(ctx.channel(), e);
                    }
                });
            }
        }catch (Throwable e) {
            // 异常
            nettyEventListener.caught(ctx.channel(), e);
        }
    }

    /**
     * 写出， 向上传递
     */
    @SuppressWarnings("all")
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        try {
            logger.debug("[MISC] sent {}", msg);
            nettyEventListener.sent(ctx.channel(), (OUTBOUND)msg);
        }finally {
            super.write(ctx, msg,  promise);
        }
    }

    /**
     * 异常， 不向下传递， 不然会大量报错
     */
    @SuppressWarnings("all")
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warn("[MISC] caught address: {}, exception: {}", ctx.channel().remoteAddress(), cause.getMessage());
        nettyEventListener.caught(ctx.channel(), cause);
    }
}
