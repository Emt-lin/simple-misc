package com.misc.core.spi;

/**
 * TODO
 *
 */

public class ITestChild implements TestChild {


    @Override
    public void echo(String msg) {
        System.out.println("嘤嘤嘤 : " + msg);
    }
}
