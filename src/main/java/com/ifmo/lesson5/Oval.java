package com.ifmo.lesson5;

public class Oval extends Shape {

    private double a;
    private double b;

    public Oval(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    @Override
    public double area() {
        return Math.PI * this.a * this.b;
    }
}
