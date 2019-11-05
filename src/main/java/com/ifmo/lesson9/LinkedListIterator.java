package com.ifmo.lesson9;

import java.util.Iterator;

public class LinkedListIterator<T> implements Iterator<T> {

    private Item<T> head;

    public LinkedListIterator(Item<T> head) {
        this.head = head;
    }

    @Override
    public boolean hasNext() {
        return (head == null) ? false : true;
    }

    @Override
    public T next() {
        T res = head.value;
        head = head.next;
        return res;
    }
}
