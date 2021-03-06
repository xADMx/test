package com.ifmo.lesson5;


/**
 * Односвязный список, где каждый предыдущий
 * элемент харнит ссылку на следующий. Список
 * оканчивается ссылкой со значением {@code null}.
 */
public class LinkedList {
    /**
     * Ссылка на первый элемент списка.
     */
    private Item head;
    private int count = 0;

    /**
     * Добавляет значение в конец списка.
     *
     * @param val Значение, которое будет добавлено.
     */
    public void add(Shape val) {
        // TODO implement

        if(head == null) {
            head = new Item(val);
        } else {
            Item item = head;
            while (item.next != null) {
                    item = item.next;
            }
            item.next = new Item(val);

        }
        this.setCount(1);
    }

    /**
     * Извлекает значение из списка по индексу.
     *
     * @param i Индекс значения в списке.
     * @return Значение, которое находится по индексу
     * или {@code null}, если не найдено.
     */
    public Shape get(int i) {
        // TODO implement
        Item obj = this.find(i);
        return (obj == null) ? null : obj.value;
    }

    private synchronized void setCount(int count){
        this.count += count;
    }

    public int getCount() {
        return count;
    }

    /**
     * Удаляет значение по индексу и возвращает
     * удаленный элемент.
     *
     * @param i Индекс, по которому будет удален элемент.
     * @return Удаленное значение или {@code null}, если не найдено.
     */
    public Shape remove(int i) {
        // TODO implement
        Item obj = this.find(i - 2);

        if (obj != null) {
            Shape res = obj.next.value;
            obj.next = obj.next.next;
            this.setCount(-1);
            return res;
        } else {
            return null;
        }
    }

    private Item find(int i) {
        Item obj = head;
        for (int j = 0; j < i; j++) {
            if (obj == null)
                return null;

            obj = obj.getNext();
        }
        return obj;
    }
}
