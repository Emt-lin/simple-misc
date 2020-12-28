package com.misc.core.netty;

/**
 * todo
 */
public interface NettyNode {

    /**
     * 启动Netty
     */
    NettyNode start() throws Throwable;

    /**
     * 关闭Netty
     */
    NettyNode close() throws Throwable;

    /**
     * sync 的作用就是阻塞，知道netty服务器关闭
     */
    NettyNode sync() throws Throwable;
}
