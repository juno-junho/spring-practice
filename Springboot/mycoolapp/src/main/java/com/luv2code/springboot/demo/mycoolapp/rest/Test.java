package com.luv2code.springboot.demo.mycoolapp.rest;

import java.util.Comparator;
import java.util.Objects;
import java.util.Random;

public class Test{
    String name;

    // 1
    void setName(String name){
        if (name == null ) throw new NullPointerException("name must be not null");
        this.name = name;
    }
    // 2
    void setName2(String name){
        this.name = Objects.requireNonNull(name,"name must be not null");
    }
}
