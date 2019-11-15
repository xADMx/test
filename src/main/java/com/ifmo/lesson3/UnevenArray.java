package com.ifmo.lesson3;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class UnevenArray {
    /*
    Создайте массив из всех нечётных чисел от 1 до 99, выведите его на экран в строку, а затем
    этот же массив выведите на экран тоже в строку, но в обратном порядке (99 97 95 93 … 7 5 3
    1)
     */
    public static void main(String[] args) {
        int[] unevenArray = unevenArray();

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

    public static int[] unevenArray() {
        // TODO implement

        int[] arr = new int[50];
        int[] arrRev = new int[50];
        int index = 0;

        for (int i = 1; i < 100; i++) {
            if (i % 2 != 0) {
                arr[index] = i;
                index++;
            }
        }

       // System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length ; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println("");

        for (int i = 0; i < arr.length; i++) {
            arrRev[i] = arr[(arr.length - 1) - i];
        }
       // System.out.println(Arrays.toString(arrRev));
        for (int i = 0; i < arrRev.length ; i++) {
            System.out.print(arrRev[i] + " ");
        }
        return arr;
    }
}
