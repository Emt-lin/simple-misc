package com.misc.core.register;

import com.misc.core.exception.RegisterException;
import com.misc.core.spi.SPIUtil;

/**
 * RegisterFactory 获取注册中心
 */
public class RegisterFactory {

    /**
     * 这里没有考虑多服务
     */
    public static RegistryService createRegistry() throws RegisterException {
//        return SPIUtil.loadFirstInstanceOrDefault(RegistryService.class, RedisRegistryService.class);
        return null;
    }
}
