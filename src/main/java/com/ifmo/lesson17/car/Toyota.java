package com.ifmo.lesson17.car;

public class Toyota implements Car {
    @Override
    public int power() {
        return 140;
    }

    @Override
    public int maxSpeed() {
        return 200;
    }
}
