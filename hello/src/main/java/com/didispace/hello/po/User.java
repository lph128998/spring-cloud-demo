package com.didispace.hello.po;

/**
 * @author liupeihua
 * @date 2018/3/22 下午5:10
 */
public class User {
    private String name;

    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "name=" + name + ", age=" +age;
    }
}
