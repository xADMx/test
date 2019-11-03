package com.ifmo.lesson3;

import java.util.Arrays;
import java.util.Random;

public class Random8 {
    /*
    Создайте массив из 8 случайных целых чисел из отрезка [1;10]. Выведите массив на экран
    в строку. Замените каждый элемент с нечётным индексом на ноль. Снова выведете массив на
    экран на отдельной строке.
     */
    public static void main(String[] args) {
        int[] randomNumbers = randomNumbers();
        System.out.println(Arrays.toString(randomNumbers));

        // TODO implement

        int[] replacedWithZeros = replaceWithZeros(randomNumbers);
        System.out.println(Arrays.toString(replacedWithZeros));
        // TODO implement
    }

    public static int[] randomNumbers() {
        Random rnd = new Random();

        int[] arr = new int[8];
        for (int i = 0; i < 8; i++) {
            arr[i] = rnd.nextInt(10) + 1;
        }
        return arr;
    }

    public static int[] replaceWithZeros(int[] randomNumbers) {
        // TODO implement
        for (int i = 0; i < randomNumbers.length; i++) {
            if (i % 2 == 0)
                randomNumbers[i] = 0;
        }
        return randomNumbers;
    }
}
