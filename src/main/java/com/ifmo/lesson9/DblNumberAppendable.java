package com.ifmo.lesson9;

import java.util.Iterator;
import java.util.LinkedList;

public class DblNumberAppendable extends AbstractNumberAppendable<Double> {


    public DblNumberAppendable(ArithmeticOperation op) {
        super(op);
    }

    @Override
    public Appendable<Double> append(Double type) {
        list.add(type);
        return this;
    }

    @Override
    public Double value() {
        LinkedList<Double> d = new LinkedList<>();
        Double res = 0.0;
        Iterator<Double> iter = list.iterator();
        if(iter.hasNext())
            res = iter.next();

        for (Iterator<Double> it = iter; it.hasNext(); ) {
            res = op.operation(res, it.next());
        }
        return res;
    }

}
