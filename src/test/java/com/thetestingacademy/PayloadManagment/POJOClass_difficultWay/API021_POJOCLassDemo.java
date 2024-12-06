package com.thetestingacademy.PayloadManagment.POJOClass_difficultWay;

public class API021_POJOCLassDemo {
    // POJO: plain old java object-encapsulation( private variables fetched using getter,
    // setter method and default constructor
    private String name;
    private Integer age;

    public API021_POJOCLassDemo() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
