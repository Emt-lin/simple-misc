package com.misc.core.annotation;

import java.lang.annotation.*;

/**
 * SPI服务，越大优先级越高
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Primary {
    /**
     * 越大优先级越高
     */
    int order() default Integer.MIN_VALUE;
}
