package com.misc.spring.annotation;

import com.misc.spring.config.MiscServerConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


/**
 * EnableChatServer 开启 NettyServer
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MiscServerConfiguration.class)
public @interface EnableChatServer {
}
