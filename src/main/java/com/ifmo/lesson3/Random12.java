package com.ifmo.lesson3;

import java.util.Random;

public class Random12 {
    /*
    Создайте массив из 12 случайных целых чисел из отрезка [-15;15]. Определите какой
    элемент является в этом массиве максимальным и сообщите индекс его последнего
    вхождения в массив.
     */
    public static void main(String[] args) {
        int[] randomNumbers = randomNumbers();

        // TODO implement
        int max = max(randomNumbers);
        int maxLastIndex = lastIndexOf(randomNumbers, max);

        // TODO implement
    }

    public static int[] randomNumbers() {
        // TODO implement
        Random rnd = new Random();

        int[] arr = new int[12];
        for (int i = 0; i < 12; i++) {
            arr[i] = rnd.nextInt(31) - 15;
        }
        return arr;
    }

    public static int max(int[] randomNumbers) {
        // TODO implement
        if(randomNumbers.length < 1)
            return 0;

        int max = randomNumbers[0];
        for (int i = 1; i < randomNumbers.length; i++) {
            if (max < randomNumbers[i]) {
                max = randomNumbers[i];
            }
        }
        return max;
    }

    public static int lastIndexOf(int[] randomNumbers, int max) {
        // TODO implement
        int lastIndex = 0;
        for (int i = 0; i < randomNumbers.length; i++) {
            if (max == randomNumbers[i]) {
                lastIndex = i;
            }
        }
        return lastIndex;
    }
}
