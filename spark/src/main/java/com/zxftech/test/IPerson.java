package com.zxftech.test;

public interface IPerson {
    default void greeting(){
        System.out.println("hello world");
    }
}
