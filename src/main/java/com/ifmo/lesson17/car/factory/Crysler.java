package com.ifmo.lesson17.car.factory;

public class Crysler implements Car {
    @Override
    public int power() {
        return 250;
    }

    @Override
    public int maxSpeed() {
        return 300;
    }
}
