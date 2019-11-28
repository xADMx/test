package com.ifmo.lesson17.car.factory;

public class RUSFactory extends AFactory {
    @Override
    public Car getCar() {
        return new Lada();
    }
}
