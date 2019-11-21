package com.ifmo.lesson17.car;

public class Lada implements Car {
    @Override
    public int power() {
        return 80;
    }

    @Override
    public int maxSpeed() {
        return 180;
    }
}
