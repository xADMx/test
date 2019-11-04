package com.ifmo.lesson9;

public class DblNumberAppendable extends AbstractNumberAppendable<Double> {

    public DblNumberAppendable(ArithmeticOperation op) {
        super(op);
    }

    @Override
    public Appendable<Double> append(Double type) {
        return null;
    }

    @Override
    public Double value() {
        return null;
    }

}
