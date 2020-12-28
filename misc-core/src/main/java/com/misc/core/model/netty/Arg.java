package com.misc.core.model.netty;

import com.alibaba.fastjson.JSON;

/**
 * 参数对象 -> RPC调用时封装的信息，这里考虑为什么我要转成JSON将每一个对象
 * 第一：如果使用接口上的类型，作为反序列类型，那么会出现很多类型和传入类型不一致的问题，参数是map，
 * 可以我传入hashMap，那么反序列化成MAP的话，会丢失很多问题
 *
 * 第二：就是内部类无法处理，所以很麻烦，这点，不知道如何处理？？？
 */
public class Arg {

    /**
     * 索引
     */
    private int index;

    /**
     * 类型
     */
    private Class<?> clazz;

    /**
     * 对象值，JSON值，省的服务器再转
     */
    private String value;

    @Override
    public String toString() {
        return "Arg{" +
                "index=" + index +
                ", clazz=" + clazz +
                ", value='" + value + '\'' +
                '}';
    }

    public Arg(){

    }

    public Arg(int index, Class<?> clazz, String value) {
        this.index = index;
        this.clazz = clazz;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static Arg of(int index, Object object) {
        return new Arg(index, object.getClass(), JSON.toJSONString(object));
    }
}
