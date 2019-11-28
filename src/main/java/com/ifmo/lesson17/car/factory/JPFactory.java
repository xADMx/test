package com.ifmo.lesson17.car.factory;

public class JPFactory extends AFactory {
    @Override
    public Car getCar() {
        return new Toyota();
    }
}
