package com.ifmo.lesson9;

import java.util.Iterator;
import java.util.LinkedList;

public class IntNumberAppendable extends AbstractNumberAppendable<Integer> {


    public IntNumberAppendable(ArithmeticOperation op) {
        super(op);
    }

    @Override
    public Appendable<Integer> append(Integer type) {
        list.add(type);
        return this;
    }

    @Override
    public Integer value() {
        LinkedList<Double> d = new LinkedList<>();
        Integer res = 0;
        Iterator<Integer> iter = list.iterator();
        if(iter.hasNext())
            res = iter.next();

        for (Object item : list) {
            res += op.operation(res, (Integer) item);
        }
        return res;
    }

}
