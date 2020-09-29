package com.example;

import com.example.testlink.ChildBean;

import java.util.List;

/**
 * Create by lxx
 * Date : 2020/9/29 10:13
 * Use by
 */
public class ParentBean {
    private String name;
    private List<ChildBean> childBeans;

    public List<ChildBean> getChildBeans() {
        return childBeans;
    }

    public void setChildBeans(List<ChildBean> childBeans) {
        this.childBeans = childBeans;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
