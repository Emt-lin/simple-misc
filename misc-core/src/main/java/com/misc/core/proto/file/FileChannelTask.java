package com.misc.core.proto.file;

import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * todo
 */
public class FileChannelTask {

    /**
     * 文件流
     */
    private FileOutputStream fileOutputStream;

    /**
     * 文件管道
     */
    private FileChannel fileChannel;

    /**
     * 文件的更新操作时间
     */
    private long timestamp;

    /**
     * 文件信息
     */
    private String fileName;
}
