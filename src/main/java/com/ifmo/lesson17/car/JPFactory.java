package com.ifmo.lesson17.car;

public class JPFactory extends AFactory {
    @Override
    public Car getCar() {
        return new Toyota();
    }
}
