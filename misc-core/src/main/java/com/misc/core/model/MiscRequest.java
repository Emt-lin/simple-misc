package com.misc.core.model;


import lombok.Getter;
import lombok.Setter;


/**
 * @author psl
 * @date 2020/11/20
 */
@Getter
@Setter
public abstract class MiscRequest extends MiscMessage{

    /**
     * 请求头
     */
    protected URL url;

    /**
     * 服务版本号
     */
    protected short serverVersion;

    /**
     * 时间戳
     */
    protected long timeStamp;

    /**
     * 进程id
     */
    protected int pid;

    /**
     * key
     */
    protected long key;
}
