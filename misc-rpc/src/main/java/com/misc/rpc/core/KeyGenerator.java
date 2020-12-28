package com.misc.rpc.core;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 用来生产key
 */
public interface KeyGenerator {

    /**
     * string 的原因是通用
     */
    String getKey();

    /**
     * 默认的生成器，这个必须是全局的自增器
     */
    AtomicLong counter = new AtomicLong();
    KeyGenerator DEFAULT_KEY_GENERATOR = () -> String.valueOf(counter.getAndIncrement());
}
