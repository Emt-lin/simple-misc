package com.misc.core.handler;

import com.misc.core.exception.HandlerException;
import com.misc.core.listener.MiscEvent;

/**
 * 事件处理器 {@link MiscEvent} {@link com.misc.core.listener.MiscEventType}
 * <p>
 *     采用 策略（Strategy）模式
 *     来处理不同的事件类型
 * </p>
 */
public interface MiscEventHandler {

    void handler(MiscEvent event) throws HandlerException;

    MiscEventHandler DEFAULT_MISC_EVENT_HANDLER = event -> {

    };
}
