package com.misc.core.listener;

/**
 * 启动过的事件，
 * {@link com.misc.core.handler.MiscEventHandler} 处理器
 */
public interface MiscEvent {

    String NULL = "NULL MESSAGE ! ";

    /**
     * {@link MiscEventType} This is the type of HandlerType .
     * {@link com.misc.core.handler.MiscEventHandler} This is the handler of front type .
     *
     * @return MiscEventType
     */
    MiscEventType eventType();

    /**
     * OTHER MESSAGE
     *
     * @return Object This the obj because of no declare type .
     */
    default Object event() {
        return NULL;
    }
}
