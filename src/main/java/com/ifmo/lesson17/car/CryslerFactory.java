package com.ifmo.lesson17.car;

public class CryslerFactory extends AFactory {
    @Override
    public Car getCar() {
        return new Crysler();
    }
}
