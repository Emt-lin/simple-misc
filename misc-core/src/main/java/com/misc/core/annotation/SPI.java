package com.misc.core.annotation;

import java.lang.annotation.*;

/**
 * 框架中使用的SPI接口，都申明了此注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SPI {
    String value() default "";
}
