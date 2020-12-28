package com.misc.core.proto;

import com.misc.core.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * todo
 */
public class TypeConstants {
    public static final String RPC_TYPE = "rpc";
    public static final String HEART_TYPE = "heart";
    public static final Set<String> TYPE_SET = new HashSet<>();

    static {
        TYPE_SET.add(RPC_TYPE);
        TYPE_SET.add(HEART_TYPE);
    }

    public static boolean validateType(String type) {
        if(StringUtils.isEmpty(type)) {
            return false;
        }
        return TYPE_SET.contains(type);
    }
}
