package com.ifmo.lesson9;

import java.util.Iterator;

public class ArrayListIterator<T> implements Iterator {

    private Object[] values;
    private int index = 0;

    public ArrayListIterator(Object[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return (values.length > index);
    }

    @Override
    public T next() {
        return (T) values[index++];
    }
}
