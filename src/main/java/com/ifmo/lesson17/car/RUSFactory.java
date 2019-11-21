package com.ifmo.lesson17.car;

public class RUSFactory extends AFactory {
    @Override
    public Car getCar() {
        return new Lada();
    }
}
