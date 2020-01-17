package com.zxftech.test;

import java.io.*;

public class OC implements ICar, IPerson {
    String name;
    String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static void main(String[] args) throws IOException {
        Writer writer = new FileWriter(new File("d://zz.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
//        new ICar(){}.greeting();
        long start = System.nanoTime();
        for (int i = 0; i < 1; i++) {

        }
        long end = System.nanoTime();
        System.out.println("wast:" + (end - start));
    }

    @Override
    public void greeting() {
        ICar.super.greeting();
        IPerson.super.greeting();
    }
}
