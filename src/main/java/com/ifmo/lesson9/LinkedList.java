package com.ifmo.lesson9;

import java.util.Iterator;

/**
 * Односвязный список, где каждый предыдущий
 * элемент харнит ссылку на следующий. Список
 * оканчивается ссылкой со значением {@code null}.
 */
public class LinkedList<T> implements List<T>, Stack<T>, Queue<T> {
    /** Ссылка на первый элемент списка. */
    private Item<T> head;

    /** {@inheritDoc} */
    @Override
    public void add(T val) {
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
    public T take() {
        // TODO implement.
        if(head == null)
            return null;

        T res = head.value;
        head = head.next;
        return res;
    }

    /** {@inheritDoc} */
    @Override
    public T get(int i) {
        // TODO implement.

        Item<T> obj = this.find(i);
        return (obj == null) ? null : obj.value;
    }

    /** {@inheritDoc} */
    @Override
    public T remove(int i) {
        // TODO implement.
        if(head == null)
            return null;

        if(i == 0){
            T res = head.value;
            head = head.next;
            return res;
        }

        Item<T> obj = this.find(i - 1);

        if (obj != null) {
            T res = obj.next.value;
            obj.next = obj.next.next;
            return res;
        } else {
            return null;
        }
    }

    @Override
    public int size() {
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    public Iterator iterator() {
        // TODO implement.
        return new LinkedListIterator<T>(head);
    }

    /** {@inheritDoc} */
    @Override
    public void push(Object value) {
        // TODO implement.
        Item<T> item = new Item(value);
        item.next = head;
        head = item;
    }

    /** {@inheritDoc} */
    @Override
    public T pop() {
        // TODO implement.
        if(head == null)
            return null;

        T res = head.value;
        head = head.next;
        return res;
    }

    private Item find(int i) {
        Item<T> obj = head;
        for (int j = 0; j < i; j++) {
            if (obj == null)
                return null;

            obj = obj.next;
        }
        return obj;
    }
}
