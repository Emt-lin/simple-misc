package com.misc.core.proto.misc.common;

/**
 * 定义了一些编解码需要的常量
 *
 * @author psl
 * @date 2020/11/20
 */
public interface MiscProtoConstance {

    /**
     * 魔数位
     */
    byte MAGIC_NUMBER = 0XF;

    /**
     * 文件开始标志位
     */
    byte FILE_START = 126;

    /**
     * 文件结束标志位
     */
    byte FILE_END = 127;

    /**
     * 文件是否需要响应结果
     */
    byte FILE_NEED_ACK = 12;

    /**
     * 不需要响应结果
     */
    byte FILE_NOT_ACK = 13;
}
