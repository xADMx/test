package com.ifmo.lesson17.car;

public class main {
    public static void main(String[] args) {
        System.out.println(AFactory.getFactory("RUS").getCar());
    }
}
