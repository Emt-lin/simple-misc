package com.misc.core.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.Promise;
import org.junit.Test;


import java.nio.charset.StandardCharsets;

/**
 * todo
 *
 */
public class NettyServer {


    public static void main(String[] args)  {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        ServerBootstrap group = serverBootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup());
        group.channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new ChannelDuplexHandler() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println("read-1");
                                super.channelRead(ctx, msg);
                            }

                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                System.out.println("write-1");
                                super.write(ctx, msg, promise);
                            }

                            @Override
                            public void flush(ChannelHandlerContext ctx) throws Exception {
                                System.out.println("flush-1");
                                super.flush(ctx);
                            }
                        });


                        pipeline.addLast(new ChannelDuplexHandler() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println("read-2");
                                ByteBuf byteBuf = ctx.alloc().directBuffer();
                                byteBuf.writeCharSequence("byteBuf", StandardCharsets.UTF_8);
                                ctx.writeAndFlush(byteBuf);
                            }

                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                System.out.println("write-2");
                                super.write(ctx, msg, promise);
                            }


                            @Override
                            public void flush(ChannelHandlerContext ctx) throws Exception {
                                System.out.println("flush-2");
                                super.flush(ctx);
                            }
                        });
                    }
                });

        ChannelFuture sync = null;
        try {
            sync = group.bind(8888).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() throws IllegalAccessException, InstantiationException {
        Void aVoid = Void.TYPE.newInstance();
        System.out.println(aVoid);
    }
}
