package com.misc.core.util;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * todo
 */
public class SystemUtil {
    /**
     * 获取当前jvm进程的pid
     * @return
     */
    public static int getCurrentProcessPid(){
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        String name = runtimeMXBean.getName();
        int index = name.indexOf("@");
        return Integer.parseInt(name.substring(0, index));
    }
}
