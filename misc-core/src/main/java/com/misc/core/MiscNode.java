package com.misc.core;

/**
 * Server 节点
 */
public interface MiscNode {

    /**
     * 初始化 init
     * @throws Exception
     */
    default void init() throws Exception {

    }

    /**
     * 启动
     * @throws Exception
     */
    void start() throws Exception;

    /**
     * 关闭
     * @throws Exception
     */
    void stop() throws Exception;
}
