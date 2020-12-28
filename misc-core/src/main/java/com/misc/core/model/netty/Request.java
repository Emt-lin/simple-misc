package com.misc.core.model.netty;

import com.misc.core.model.URL;

import java.io.Serializable;
import java.util.Arrays;

import static com.misc.core.model.UrlConstants.*;

/**
 * 请求对象，可以通过Npack 进行组装
 */
public class Request implements Serializable {

    private static final long serialVersionUID = -2385269869425446138L;

    /**
     * 消息ID，唯一表示
     */
    private String id;

    /**
     * 消息协议，指的是做什么事情
     */
    private String protocol;

    /**
     * 消息URL
     */
    private URL url;

    /**
     * 消息体
     */
    private byte[] body;

    /**
     * 超时时间， -1代表永远不超时
     */
    private long timeout;

    /**
     * 时间戳，代表发送事件的时候
     */
    private long timestamp;

    /**
     * 请求的地址
     */
    private String host;

    /**
     * 请求端口
     */
    private int port;

    /**
     * 这个是请求的server版本号
     */
    private short version;

    /**
     * 构造方法，类似于HTTP
     * @param url URL
     * @param body 数据体
     * @param timestamp 时间戳
     */
    public Request(URL url, byte[] body, long timestamp) {
        this.url = url;
        this.protocol = url.getProtocol();
        this.id = url.getParameter(ID_KEY);
        this.body = body;
        this.timestamp = timestamp;
        this.timeout = url.getParameter(TIMEOUT_KEY, Long.MAX_VALUE);
    }

    public Request(URL url, byte[] body, long timestamp, String host, int port, short version) {
        this(url, body, timestamp);
        this.host = host;
        this.port = port;
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public String getProtocol() {
        return protocol;
    }

    public URL getUrl() {
        return url;
    }

    public byte[] getBody() {
        return body;
    }

    public long getTimeout() {
        return timeout;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id='" + id + '\'' +
                ", protocol='" + protocol + '\'' +
                ", url=" + url +
                ", body=" + convert() +
                ", timeout=" + timeout +
                ", timestamp=" + timestamp +
                '}';
    }

    public void release() {
        this.body = null;
        this.url = null;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public short getVersion() {
        return version;
    }

    // 查看是否需要ACK
    public boolean needACK() {
        return this.url.getParameter(ACK_KEY, NO_ACK).equals(IS_ACK);
    }

    private static final String NULL = "";

    private String convert() {
        if(this.body == null || body.length == 0) {
            return NULL;
        }else {
            return new String(body);
        }
    }
}
