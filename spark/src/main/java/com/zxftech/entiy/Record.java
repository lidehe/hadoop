package com.zxftech.entiy;

import java.io.Serializable;

public class Record implements Serializable {
    private String name;
    private String address;
    private String gender;

    public Record(String name, String address, String gender) {
        this.name = name;
        this.address = address;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return getName() + " " + getAddress() + " " + getGender();
    }

}
