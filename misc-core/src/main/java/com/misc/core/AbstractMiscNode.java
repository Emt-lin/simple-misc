package com.misc.core;

import com.misc.core.listener.MiscEventListener;
import com.misc.core.listener.MiscEvent;
import com.misc.core.proto.ProtocolType;
import com.misc.core.proto.misc.common.MiscProperties;
import com.misc.core.proto.misc.common.MiscSerializableType;
import com.misc.core.proto.misc.serial.*;
import com.misc.core.util.ThreadPool;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import static com.misc.core.commons.Constants.*;
import static com.misc.core.commons.Constants.DEFAULT_THREAD_NAME;
import static com.misc.core.commons.PropertiesConstant.*;

/**
 * 配置属性
 */
public abstract class AbstractMiscNode implements MiscNode{

    private static final long serialVersionUID = -1257564495480060947L;

    /**
     * netty 绑定的ip
     */
    protected InetSocketAddress address;

    /**
     * 启动监听器
     * 可以看 {@link MiscEvent SERVER_SUCCESS} 属性去判断 失败/成功
     */
    protected MiscEventListener listener;

    /**
     * 线程池
     */
    protected ThreadPool threadPool;

    /**
     * 属性
     */
    protected MiscProperties properties;

    /**
     * 编解码处理器
     */
    protected final Map<Byte, MiscSerializableHandler> serializeHandlerMap = new HashMap<>();

    /**
     * 协议类型
     */
    protected ProtocolType protocolType;

    /**
     * 心跳
     */
    protected int heartInterval;

    public AbstractMiscNode(MiscEventListener listener, ThreadPool threadPool,
                            MiscProperties properties, ProtocolType protocolType) {
        this.properties = properties == null ? new MiscProperties() : properties;

        this.address = new InetSocketAddress(this.properties.getProperty(CLIENT_HOST, DEFAULT_HOST),
                this.properties.getInt(CLIENT_PORT, DEFAULT_PORT));

        this.heartInterval = this.properties.getInt(SERVER_HEART_INTERVAL, DEFAULT_SERVER_HEART_INTERVAL);
        this.listener = listener == null ? event -> {
        } : listener;
        this.threadPool = threadPool == null ? new ThreadPool(DEFAULT_THREAD_SIZE, DEFAULT_THREAD_QUEUE_SIZE,
                DEFAULT_THREAD_NAME) : threadPool;
        this.protocolType = protocolType;
    }

    /**
     * 初始化处理器
     */
    public static void initSerializeHandleMap(Map<Byte, MiscSerializableHandler> codecHandlerMap) {

        // 初始化Map
        codecHandlerMap.put(MiscSerializableType.MESSAGE_PACK.getCode(), new MessagePackSerializableType());
        codecHandlerMap.put(MiscSerializableType.MESSAGE_PACK_GZIP.getCode(), new GzipMessagePackSerializableType());
        codecHandlerMap.put(MiscSerializableType.JSON.getCode(), new JsonSerializableType());
        codecHandlerMap.put(MiscSerializableType.Java_Serializable.getCode(), new ByteSerializableType());
    }

    /**
     * 处理器
     */
    protected static InetSocketAddress getAddress(MiscProperties properties) {
        return new InetSocketAddress(properties.getProperty(CLIENT_HOST, DEFAULT_HOST),
                properties.getInt(CLIENT_PORT, DEFAULT_PORT));
    }
}
