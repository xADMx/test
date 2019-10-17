package com.ifmo.lesson3;

import java.util.Random;

public class TwoArrays {
    /*
     Создайте 2 массива из 5 случайных целых чисел из отрезка [0;5] каждый, выведите
     массивы на экран в двух отдельных строках. Посчитайте среднее арифметическое элементов
     каждого массива и сообщите, для какого из массивов это значение оказалось больше (либо
     сообщите, что их средние арифметические равны).
     */
    public static void main(String[] args) {
        int[] randomNumbers1 = randomNumbers();
        int[] randomNumbers2 = randomNumbers();

        // TODO implement
        // А тут точно инт????
        int average1 = average(randomNumbers1);
        int average2 = average(randomNumbers2);

        if (average1 > average2) {
            System.out.println("Среднее арифметическое первого массива больше второго.");
        } else if (average1 < average2) {
            System.out.println("Среднее арифметическое второго массива больше первого.");
        } else {
            System.out.println("Среднее арифметическое первого массива и второго равны.");
        }

        // TODO implement
    }

    public static int[] randomNumbers() {
        // TODO implement
        Random rnd = new Random();

        int[] arr = new int[5];
        for (int i = 0; i < 5; i++) {
            arr[i] = rnd.nextInt(5);
        }
        return arr;
    }

    public static int average(int[] randomNumbers) {
        // TODO implement
        int sum = 0;
        for (int i = 0; i < randomNumbers.length; i++) {
            sum += randomNumbers[i];
        }
        return sum / randomNumbers.length;
    }
}
