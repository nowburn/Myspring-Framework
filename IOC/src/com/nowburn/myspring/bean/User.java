package com.nowburn.myspring.bean;

/**
 * Auther: hope email: pangxianran@hyperchain.cn
 * Date: 2018/10/18 上午8:48
 * Description:
 **/
public class User {

    private Address address;
    private String age;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    public void say(String str) {
        System.out.println("User say:" + str);
    }


    @Override
    public String toString() {
        return "User{" +
                "address=" + address +
                ", age='" + age + '\'' +
                '}';
    }
}
