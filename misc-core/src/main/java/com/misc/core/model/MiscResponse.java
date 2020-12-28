package com.misc.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 响应
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class MiscResponse extends MiscMessage{

    private static final long serialVersionUID = -5358957269465165885L;

    /**
     * 请求头
     */
    private URL url;

    /**
     * 服务版本号
     */
    private short serverVersion;

    /**
     * 时间戳
     */
    private long timestamp;

    /**
     * 进程id
     */
    private int pid;
}
