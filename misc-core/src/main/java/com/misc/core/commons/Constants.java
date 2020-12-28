package com.misc.core.commons;

import com.misc.core.proto.ProtocolType;
import com.misc.core.proto.misc.common.MiscSerializableType;
import com.misc.core.util.SystemUtil;

import static com.misc.core.commons.PropertiesConstant.*;

/**
 * 存放所有的常量信息，客服端服务端同理
 */
public final class Constants {
    /**
     * 默认的上下文名称
     */
    public static final String DEFAULT_CONTEXT_NAME = "Misc-Context";

    /**
     * 服务版本号，默认是1，双方约定好的
     */
    public static final short DEFAULT_SERVER_VERSION = Short.parseShort(
            System.getProperty(CLIENT_SERVER_VERSION, "1"));

    /**
     * 协议类型，默认是 misc协议
     */
    public static final ProtocolType DEFAULT_PROTOCOL_TYPE = ProtocolType.MISC_PROTO;

    /**
     * 序列化类型，默认是 message pack
     */
    public static final MiscSerializableType DEFAULT_SERIALIZABLE_TYPE = MiscSerializableType.BYTE_ARRAY;


    /**
     * 默认的地址
     */
    public static final String DEFAULT_HOST = System.getProperty(CLIENT_HOST, "localhost");
    public static final int DEFAULT_PORT = Integer.getInteger(CLIENT_PORT, 9999);

    /**
     * 默认处理器个数
     */
    public static final int DEFAULT_IO_THREADS = Math.min(Runtime.getRuntime().availableProcessors() + 1, 32);

    /**
     * 线程名字
     */
    public static final String DEFAULT_THREAD_NAME = "Netty-Handler";

    /**
     * 默认大小是50个线程
     */
    public static final int DEFAULT_THREAD_SIZE = 50;

    /**
     * 无界队列，默认-1
     */
    public static final int DEFAULT_THREAD_QUEUE_SIZE = -1;

    /**
     * 请求超时时间，默认2000ms
     */
    public static final long DEFAULT_REQUEST_TIMEOUT = Long.getLong(CLIENT_REQUEST_TIMEOUT, 2000L);

    /**
     * 连接超时时间，默认2000ms
     */
    public static final long DEFAULT_CONNECT_TIMEOUT = Long.getLong(CLIENT_CONNECT_TIMEOUT, 2000L);

    /**
     * 客户端默认值 心跳时间45s
     */
    public static final int DEFAULT_CLIENT_HEART_INTERVAL = Integer.getInteger(CLIENT_HEART_INTERVAL, 30);

    /**
     * 服务器默认心跳时间，90s
     */
    public static final int DEFAULT_SERVER_HEART_INTERVAL = Integer.getInteger(SERVER_HEART_INTERVAL, 60);

    /**
     * 系统的文件分隔符
     */
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * 默认的保存文件夹
     */
    private static final String FILE_tmp = "file";

    /**
     * 默认的保存路径
     */
    public static final String DEFAULT_FILE_DIR = getFileDir();

    /**
     * 保存文件的位置，设置
     */
    private static String getFileDir() {
        String file = System.getProperty(CLIENT_FILE_DIR);
        if(file == null || file.isEmpty()) {
            file = System.getProperty("user.dir") + FILE_SEPARATOR + FILE_tmp;
        }
        return file;
    }


    /**
     * 默认的清空文件管道的时间
     */
    public static final int DEFAULT_CLEAR_FILE_CHANNEL_TIME = 1000;

    /**
     * 默认的打开文件的数量
     */
    public static final int DEFAULT_OPEN_FILE_COUNT = 100;

    /**
     * 获取当前jvm进程的pid
     */
    public static final int CURRENT_PROCESS_PID = SystemUtil.getCurrentProcessPid();

    /**
     * 设置默认的最小等待时间
     */
    public static final int DEFAULT_MIN_WAIT_TIME = 2000;
    /**
     * 设置默认的 LRU缓存的大小
     */
    public static final int DEFAULT_LRU_CACHE_SIZE = 128;
}
