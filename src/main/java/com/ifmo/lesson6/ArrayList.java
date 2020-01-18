package com.ifmo.lesson6;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Этот класс должен реализовывать следующие методы: add(), get(), remove() и iterator() из интерфейса List.
 * Если при выполнении add() в массиве нет свободных элементов, то создать новый - вдвое больше,
 * скопировать в него все значения из старого и + 1, который сейчас добавляется.
 * Удаление должно сдвинуть все элементы влево, если это требуется.
 * Например, если список с такими элементами:
 * |0|1|2|3|4|5|
 * Удаляем элемент по индексу 2:
 * |0|1|_|3|4|5|
 * Перемещаем все элементы влево:
 * |0|1|3|4|5|_|
 * Теперь при итерации по ним после 1 будет идти сразу 3, как в связном списке.
 */
public class ArrayList implements List {
    private static final int DEFAULT_SIZE = 10;

    private Object[] values;

    /**
     * Создаёт новый {@link #ArrayList} с размером внутреннего массива по умолчанию.
     */
    public ArrayList() {
        this(DEFAULT_SIZE);
    }

    /**
     * Создаёт новый {@link #ArrayList} с размером внутреннего массива,
     * равного {@code initialSize}.
     *
     * @param initialSize Начальный размер внутреннего массива.
     */
    public ArrayList(int initialSize) {
        values = new Object[initialSize];
    }

    /** {@inheritDoc} */
    @Override
    public void add(Object val) {
        // TODO implement.
        int i = 0;
        boolean out = false;

        while (true) {

            for (; i < values.length; i++) {
                if (values[i] == null) {
                    values[i] = val;
                    out = true;
                    break;
                }
            }

            if(out)
                break;
            values = Arrays.copyOf(values, values.length * 2);

        }
    }

    /** {@inheritDoc} */
    @Override
    public Object get(int i) {
        // TODO implement.
        return (i >= values.length) ? null : values[i];
    }

    /** {@inheritDoc} */
    @Override
    public Object remove(int i) {
        // TODO implement.
        if (i < 0 || i >= values.length)
            return null;

        Object result = values[i];

        for (int j = i + 1; j < values.length; j++) {
                values[j - 1] = values[j];
        }

        values[values.length - 1] = null;

        return result;
    }

    /** {@inheritDoc} */
    @Override
    public Iterator iterator() {
        // TODO implement.

        return new ArrayListIterator(values);
    }
}
