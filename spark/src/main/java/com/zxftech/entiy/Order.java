package com.zxftech.entiy;

import java.io.Serializable;

public class Order implements Serializable {
    private String name;
    private String social_no;
    private String address;
    private String phone;
    private String goods;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSocial_no() {
        return social_no;
    }

    public void setSocial_no(String social_no) {
        this.social_no = social_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return getName()+" "+getAddress()+" "+getGoods()+" "+getPhone()+" "+getSocial_no();
    }
}
