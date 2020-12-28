package com.misc.core.model;

/**
 * release 释放引用（提早释放内存）
 */
public interface Releasable {

    void release();
}
