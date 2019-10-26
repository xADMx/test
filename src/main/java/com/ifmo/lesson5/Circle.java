package com.ifmo.lesson5;

public class Circle extends Oval {
    public Circle(double r) {
        super(r, 0);
    }

    @Override
    public double area() {
        return Math.PI * this.getA() * this.getA();
    }
}
