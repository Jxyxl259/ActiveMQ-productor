package com.amq.producer.entity;

import java.util.ArrayList;
import java.util.List;

public class ClassRoom {

    private List<Student> stus = new ArrayList<>(5);

    public List<Student> getStus() {
        return stus;
    }

    public void setStus(List<Student> stus) {
        this.stus = stus;
    }
}
