package com.misc.spring.config;

import java.lang.reflect.Method;

/**
 * 启动配置类
 */
public class MiscServerConfiguration implements Cloneable{

    public void test() {

    }

    public static void main(String[] args) throws NoSuchMethodException {
        Class<MiscServerConfiguration> configurationClass = MiscServerConfiguration.class;

        Method test = configurationClass.getMethod("test");

        System.out.println(test.getDeclaringClass());

        Class<?>[] interfaces = MiscServerConfiguration.class.getInterfaces();

        for(Class<?> anInterface : interfaces) {
            System.out.println(anInterface);
        }
    }
}
