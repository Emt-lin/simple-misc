package com.misc.core.listener;

/**
 * 聊天的监听器 -> 主要是用于客户端和服务器端中
 * <p>
 *     主要是将启动 成功/失败后的操作交给了用户，这个很好的解决了我们失败重试的操作
 * </p>
 */
public interface MiscEventListener {

    void onChatEvent(MiscEvent event) throws Exception;
}
