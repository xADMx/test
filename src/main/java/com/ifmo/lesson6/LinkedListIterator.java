package com.ifmo.lesson6;

import java.util.Iterator;

public class LinkedListIterator implements Iterator {

    private Item head;

    public LinkedListIterator(Item head) {
        this.head = head;
    }

    @Override
    public boolean hasNext() {
        return (head == null) ? false : true;
    }

    @Override
    public Object next() {
        Object res = head.value;
        head = head.next;
        return res;
    }
}
