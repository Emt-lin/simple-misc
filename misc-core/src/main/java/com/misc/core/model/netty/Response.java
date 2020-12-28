package com.misc.core.model.netty;

import com.misc.core.model.URL;
import com.misc.core.model.UrlConstants;

import java.io.Serializable;
import java.util.Arrays;

/**
 *
 */
public class Response implements Serializable {

    private static final long serialVersionUID = 5478576793658402221L;

    /**
     * 消息协议
     */
    private String protocol;

    /**
     * 消息ID 唯一标识
     */
    private String id;

    /**
     * 消息的URL
     */
    private URL url;

    /**
     * 消息体
     */
    private byte[] result;

    /**
     * 时间戳
     */
    private long timestamp;

    public Response(URL url, byte[] result, long timestamp) {
        this.url = url;
        this.protocol = url.getProtocol();
        this.id = url.getParameter(UrlConstants.ID_KEY);
        this.result = result;
        this.timestamp = timestamp;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getId() {
        return id;
    }

    public URL getUrl() {
        return url;
    }

    public byte[] getResult() {
        return result;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Response{" +
                "protocol='" + protocol + '\'' +
                ", id='" + id + '\'' +
                ", url=" + url +
                ", result=" + convert() +
                ", timestamp=" + timestamp +
                '}';
    }

    private static final String NULL = "";

    private String convert() {
        if(this.result == null || this.result.length == 0) {
            return NULL;
        }else {
            return new String(result);
        }
    }
}
