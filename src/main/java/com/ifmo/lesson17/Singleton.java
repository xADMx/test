package com.ifmo.lesson17;


public class Singleton {

    public static final Singleton SG = new Singleton();

    private Singleton() {

    }

    public Singleton getInstance(){
        return SG;
    }


}
