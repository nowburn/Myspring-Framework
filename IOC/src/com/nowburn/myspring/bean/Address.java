package com.nowburn.myspring.bean;

/**
 * Auther: hope email: pangxianran@hyperchain.cn
 * Date: 2018/10/18 上午8:47
 * Description:
 **/
public class Address {

    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                '}';
    }
}
