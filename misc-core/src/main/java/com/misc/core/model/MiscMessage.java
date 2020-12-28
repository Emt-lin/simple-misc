package com.misc.core.model;

import java.io.Serializable;

/**
 *
 * todo
 * @author psl
 * @date 2020/11/20
 */
public abstract class MiscMessage implements Serializable {

    private static final long serialVersionUID = 6533402023018334751L;

    public abstract void release();
}
