package com.ifmo.lesson6;

import java.util.Iterator;

public class ArrayListIterator implements Iterator {

    private Object[] values;
    private int index = 0;

    public ArrayListIterator(Object[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return (values.length - 1 > index);
    }

    @Override
    public Object next() {
        return hasNext() ? null : values[index++];
    }
}
