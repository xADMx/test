package com.ifmo.lesson3;

import java.util.Random;

public class Random4 {
    /*
    Создайте массив из 4 случайных целых чисел из отрезка [10;99], выведите его на экран в
    строку. Определить и вывести на экран сообщение о том, является ли массив строго
    возрастающей последовательностью.
     */
    public static void main(String[] args) {
        int[] randomNumbers = randomNumbers();
        if (isIncreasingSequence(randomNumbers)) {
            System.out.println("Массив является строго возрастающей последовательностью");
        } else {
            System.out.println("Массив не является строго возрастающей последовательностью");
        }
        // TODO implement
    }

    public static int[] randomNumbers() {
        // TODO implement
        Random rnd = new Random();

        int[] arr = new int[4];
        for (int i = 0; i < 4; i++) {
            arr[i] = rnd.nextInt(100) + 10;
        }
        return arr;
    }

    public static boolean isIncreasingSequence(int[] randomNumbers) {
        // TODO implement
        for (int i = 0; i < randomNumbers.length; i++) {
            if (randomNumbers.length > i + 1 && randomNumbers[i] > randomNumbers[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
