package com.ifmo.lesson19;

public class MyClass{
    private int i = 2;
    @Exclude
    private String name = "3";
    public MyClass m;

    public MyClass() {
    }

    public MyClass(int i, String name, MyClass m) {
        this.i = i;
        this.name = name;
        this.m = m;
    }
}

