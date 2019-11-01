package com.ifmo.lesson3;

import java.util.Arrays;

public class Fibonacci20 {
    /*
    Создайте массив из 20-ти первых чисел Фибоначчи и выведите его на экран. Напоминаем,
    что первый и второй члены последовательности равны единицам, а каждый следующий —
    сумме двух предыдущих.
     */
    public static void main(String[] args) {
        int[] fibonacciNumbers = fibonacciNumbers();
        for (int i = 0; i < fibonacciNumbers.length ; i++) {
            System.out.println(fibonacciNumbers[i]);
        }
        // TODO implement
    }

    public static int[] fibonacciNumbers() {
        // TODO implement
        int[] arr = new int[20];
        for (int i = 0; i < 20; i++) {
            if (i < 2) {
                arr[i] = 1;
            } else {
                arr[i] = arr[i - 1] + arr[i - 2];
            }
        }
        return arr;
    }

}
