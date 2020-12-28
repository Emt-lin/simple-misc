package com.misc.core.listener;

/**
 * MiscEvent 的类型
 */
public enum MiscEventType {

    SERVER_START, // 启动
    SERVER_SHUTDOWN, // 关闭
    SERVER_READ, // 读
    SERVER_HANDLER_REMOVER, // handler 移除
    SERVER_CHANNEL_REGISTERED, // 管道注册

    CLIENT_CONNECTED,
    CLIENT_START,
    CLIENT_SHUTDOWN,
    CLIENT_READ,

}
