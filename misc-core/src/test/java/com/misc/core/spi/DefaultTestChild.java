package com.misc.core.spi;

/**
 * TODO
 *
 */
public class DefaultTestChild implements TestChild{


    @Override
    public void echo(String msg) {
        System.out.println("default");
    }
}
