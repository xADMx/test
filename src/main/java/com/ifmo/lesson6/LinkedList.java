package com.ifmo.lesson6;

import java.util.Iterator;

/**
 * Односвязный список, где каждый предыдущий
 * элемент харнит ссылку на следующий. Список
 * оканчивается ссылкой со значением {@code null}.
 */
public class LinkedList implements List, Stack, Queue {
    /** Ссылка на первый элемент списка. */
    private Item head;

    /** {@inheritDoc} */
    @Override
    public void add(Object val) {
        // TODO implement.
        if(head == null) {
            head = new Item(val);
        } else {
            Item item = head;
            while (item.next != null) {
                item = item.next;
            }
            item.next = new Item(val);

        }
    }

    /** {@inheritDoc} */
    @Override
    public Object take() {
        // TODO implement.
        if(head == null)
            return null;

        Object res = head.value;
        head = head.next;
        return res;
    }

    /** {@inheritDoc} */
    @Override
    public Object get(int i) {
        // TODO implement.

        Item obj = this.find(i);
        return (obj == null) ? null : obj.value;
    }

    /** {@inheritDoc} */
    @Override
    public Object remove(int i) {
        // TODO implement.
        if(head == null)
            return null;

        if(i == 0){
            Object res = head.value;
            head = head.next;
            return res;
        }

        Item obj = this.find(i - 1);

        if (obj != null) {
            Object res = obj.next.value;
            obj.next = obj.next.next;
            return res;
        } else {
            return null;
        }
    }

    /** {@inheritDoc} */
    @Override
    public Iterator iterator() {
        // TODO implement.
        return new LinkedListIterator(head);
    }

    /** {@inheritDoc} */
    @Override
    public void push(Object value) {
        // TODO implement.
        Item item = new Item(value);
        item.next = head;
        head = item;
    }

    /** {@inheritDoc} */
    @Override
    public Object pop() {
        // TODO implement.
        if(head == null)
            return null;

        Object res = head.value;
        head = head.next;
        return res;
    }

    private Item find(int i) {
        Item obj = head;
        for (int j = 0; j < i; j++) {
            if (obj == null)
                return null;

            obj = obj.next;
        }
        return obj;
    }
}
