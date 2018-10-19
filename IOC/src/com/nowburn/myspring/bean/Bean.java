package com.nowburn.myspring.bean;

import java.util.List;

/**
 * Auther: hope email: pangxianran@hyperchain.cn
 * Date: 2018/10/17 下午4:28
 * Description:
 **/
public class Bean {
    private String id;
    private String name;
    private List<Property> propertyList;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", propertyList=" + propertyList +
                '}';
    }
}
