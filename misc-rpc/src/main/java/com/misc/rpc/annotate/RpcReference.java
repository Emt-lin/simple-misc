package com.misc.rpc.annotate;

import java.lang.annotation.*;

/**
 * todo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface RpcReference {

    String version();
}
