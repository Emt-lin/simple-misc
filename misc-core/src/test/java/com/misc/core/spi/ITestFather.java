package com.misc.core.spi;

import com.misc.core.annotation.Primary;

/**
 * TODO
 *
 */
@Primary
public class ITestFather implements TestFather {

    static {
        System.out.println("static");
    }

    private TestChild testFather;
    public ITestFather() {
        System.out.println("hello ");
        this.testFather = SPIUtil.loadClass(TestChild.class, ITestFather.class.getClassLoader());
    }

    @Override
    public void echo(String name) {
        System.out.println("父亲 嘤嘤嘤");
        testFather.echo(name);
    }
}
