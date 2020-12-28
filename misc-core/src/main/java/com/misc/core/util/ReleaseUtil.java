package com.misc.core.util;

import com.misc.core.model.Releasable;

/**
 * todo
 */
public class ReleaseUtil {
    public static void release(Releasable obj) {
        obj.release();
    }
}
