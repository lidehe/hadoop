package com.zxftech.test;

public interface ICar {
    default void greeting(){
        System.out.println("hello world");
    }
}
