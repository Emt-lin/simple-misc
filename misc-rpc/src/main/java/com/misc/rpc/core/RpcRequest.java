package com.misc.rpc.core;

import com.alibaba.fastjson.JSON;
import com.misc.core.exception.ConvertException;
import com.misc.core.model.Releasable;
import com.misc.core.model.URL;
import com.misc.core.proto.ProtocolType;
import com.misc.core.proto.TypeConstants;
import com.misc.core.serialization.Deserializer;
import com.misc.core.serialization.SerializationFactory;
import com.misc.core.serialization.Serializer;
import com.misc.rpc.config.RpcConvertUtil;

import java.lang.reflect.Method;
import java.util.Map;

import static com.misc.core.model.URL.Constants.*;

/**
 * Rpc调用的参数
 * <p>
 *     1、请求某个方法以 method=method1
 *     2、需要指定反序列化的序列化的类
 * </p>
 * <p>
 *     3、以方法为单位
 *     4、方法的属性（超时：以methodName.k1、methodName.k2)
 *     5、需要告诉请求的接口类
 * </p>
 * <p>
 *     6、需要告诉调用的方法参数 -> 放在请求行中 -> 存入属性中，比如参数 methodName.params=1,2,3
 *     7、需要告诉调用的方法参数类型
 * </p>
 * -> 业务层面 拿到的对象是已经确定好的..... 需要在convert层 做全部的类型转换，不应该放到业务层
 */
public class RpcRequest implements Releasable {

    private static final long serialVersionUID = -3967657981609594301L;

    /**
     * 协议类型
     */
    private String protocol = ProtocolType.MISC_PROTO.getInfo();

    /**
     * type
     */
    private String type = TypeConstants.RPC_TYPE;

    /**
     * 接口名称
     */
    private Class<?> invokeClazz;

    /**
     * 调用对象（服务端才有这个对象）
     */
    private Object invokeTarget;

    /**
     * 调用的方法
     */
    private Method invokeMethod;

    /**
     * 方法参数类型
     */
    private Class<?>[] paramsType;

    /**
     * 方法参数
     */
    private Object[] params;

    /**
     * 当前客户端/服务端的地址
     */
    private String host;

    /**
     * 端口
     */
    private int port;

    /**
     * 核心的属性
     */
    private RpcProperties properties;

    /**
     * 序列化方法
     */
    private Serializer<Object[]> serializer;

    /**
     * 反序列化方式
     */
    private Deserializer<Object[], Class<?>[]> deserializer;

    /**
     * 唯一值， 客服端的唯一值
     */
    private String key;

    /**
     * 添加method属性
     */
    public void setMethodProperty(String key, String value) {
        checkInit();
        properties.setMethodProperties(key, value);
    }

    public void setProperty(String key, String value) {
        checkInit();
        properties.setProperties(key, value);
    }

    public void setProperties(Map<String, String> properties) {
        checkInit();
        this.properties.putAll(properties);
    }

    /**
     * 检测是否初始化
     */
    private void checkInit() {
        if(invokeMethod == null) {
            throw new NullPointerException("RpcRequest.method can not be null");
        }
        if(properties == null) {
            properties = new RpcProperties(invokeMethod);
        }
    }


    @Override
    public void release() {
        invokeClazz = null;
        invokeTarget = null;
        invokeMethod = null;
        paramsType = null;
        params = null;
        serializer = null;
        deserializer = null;
        properties.clear();
        properties = null;
    }

    /**
     * 导出
     */
    public URL toURL() throws ConvertException {
        // 检查类型
        if(!TypeConstants.validateType(type)) {
            throw new RuntimeException("can not handler type");
        }
        // 其次是设置属性
        setProperty(TYPE_KEY, type);
        if(type.equals(TypeConstants.HEART_TYPE)) {
            return new URL(protocol, host, port, properties);
        }

        // 不是rpc类型
        if(invokeClazz == null) {
            throw new ConvertException(String.format("RpcRequest invokeClass can not be NULL, %s", this));
        }

        // 是rpc，没有设置method
        if(invokeMethod == null) {
            throw new ConvertException(String.format("RpcRequest.invokeMethod can not be NULL, %s", this));
        }

        // 没有设置序列化
        if(serializer == null || deserializer == null) {
            serializer = SerializationFactory.DEFAULT_SERIALIZATION;
            deserializer = SerializationFactory.DEFAULT_SERIALIZATION;
        }

        paramsType = invokeMethod.getParameterTypes();
        // 设置方法属性
        if(paramsType != null && paramsType.length > 0) {
            setMethodProperty(PARAMS_KEY, RpcConvertUtil.convertMethodParamsTypeToString(paramsType));
        }

        if(key != null) {
            setProperty(KEY_KEY, key);
        }

        // 设置主要属性
        setProperty(METHOD_KEY, invokeMethod.getName());
        setProperty(SERIALIZER_KEY, serializer.getClass().getName());
        setProperty(DESERIALIZER_KEY, deserializer.getClass().getName());

        return new URL(protocol, host, port, invokeClazz.getName(), properties);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setParamsType(Class<?>[] paramsType) {
        this.paramsType = paramsType;
    }

    /**
     * 最简单的构造器
     */
    public RpcRequest(Class<?> invokeClazz, Method invokeMethod, Object[] params, String host, int port,
                      RpcProperties properties) {
        this.invokeClazz = invokeClazz;
        this.invokeMethod = invokeMethod;
        this.params = params;
        this.host = host;
        this.port = port;
        this.properties = properties;
    }

    public RpcRequest() {
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Class<?> getInvokeClazz() {
        return invokeClazz;
    }

    public void setInvokeClazz(Class<?> invokeClazz) {
        this.invokeClazz = invokeClazz;
    }

    public Object getInvokeTarget() {
        return invokeTarget;
    }

    public void setInvokeTarget(Object invokeTarget) {
        this.invokeTarget = invokeTarget;
    }

    public Method getInvokeMethod() {
        return invokeMethod;
    }

    public void setInvokeMethod(Method invokeMethod) {
        this.invokeMethod = invokeMethod;
    }

    public Class<?>[] getParamsType() {
        return paramsType;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public RpcProperties getProperties() {
        return properties;
    }

    public void setProperties(RpcProperties properties) {
        this.properties = properties;
    }

    public Serializer<Object[]> getSerializer() {
        return serializer;
    }

    public void setSerializer(Serializer<Object[]> serializer) {
        this.serializer = serializer;
    }

    public Deserializer<Object[], Class<?>[]> getDeserializer() {
        return deserializer;
    }

    public void setDeserializer(Deserializer<Object[], Class<?>[]> deserializer) {
        this.deserializer = deserializer;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
