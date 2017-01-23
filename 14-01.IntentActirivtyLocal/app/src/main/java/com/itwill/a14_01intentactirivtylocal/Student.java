package com.itwill.a14_01intentactirivtylocal;

import java.io.Serializable;

/**
 * Created by itwill on 2017-01-18.
 */

public class Student implements Serializable{
    private String id;
    private String name;
    private int age;
    private boolean married;

    public Student() {
    }

    public Student(String id, String name, int age, boolean married) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.married = married;
    }

    @Override
    public String toString() {
        return id+","+name+","+age+","+married;
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }
}
