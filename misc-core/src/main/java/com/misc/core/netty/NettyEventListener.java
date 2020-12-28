package com.misc.core.netty;

import com.misc.core.exception.HandlerException;
import io.netty.channel.Channel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 真正的事件监听器处理器
 */
public interface NettyEventListener<ChannelInBound, ChannelOutBound> {

    Logger logger = LoggerFactory.getLogger(NettyEventListener.class);

    /**
     * on channel connected
     */
    void connected(Channel channel) throws HandlerException;

    /**
     * on channel disconnected
     */
    void disconnected(Channel channel) throws HandlerException;

    /**
     * on message send。（不是用来发消息的，是监听回调结果的，不然会递归）
     */
    void sent(Channel channel, ChannelOutBound message) throws HandlerException;

    /**
     * on message received（同样也是监听，不然会递归）
     */
    void received(Channel channel, ChannelInBound message) throws HandlerException;

    /**
     * on exception caught
     */
    void caught(Channel channel, Throwable exception) throws HandlerException;

    /**
     * 事件触发（根据业务需求来）
     * 客户端 是发送消息
     * 服务端 是关闭连接
     */
    default void eventTriggered(Channel channel, Object event) throws HandlerException {
        if (event instanceof IdleStateEvent) {
            logger.warn("Receive heartbeat, please care!");
        }
    }


}
