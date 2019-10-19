package com.ifmo.lesson2;

public class IntsOrdering {
    /*
     В три переменные a, b и c явно записаны программистом три целых попарно неравных
     между собой числа. Создать программу, которая переставит числа в переменных таким
     образом, чтобы при выводе на экран последовательность a, b и c оказалась строго
     возрастающей.

     Числа в переменных a, b и c: 3, 9, -1
     Возрастающая последовательность: -1, 3, 9

     Числа в переменных a, b и c: 2, 4, 3
     Возрастающая последовательность: 2, 3, 4

     Числа в переменных a, b и c: 7, 0, -5
     Возрастающая последовательность: -5, 0, 7
     */
    public static void main(String[] args) {
        int a = 7, b = 0, c = -5;

        String ordering = ordering(a, b, c);

        System.out.println(ordering);
    }

    public static String ordering(int a, int b, int c) {
        // TODO implement
        boolean aB = a > b;
        boolean aC = a > c;
        boolean bC = b > c;

        int a1 = a;
        int b1 = b;
        int c1 = c;

        if (aC) {
            c1 = a;
            a1 = c;
        } else if (aB) {
            b1 = a;
            a1 = b;
        }

        if (b > c1) {
            b1 = c1;
            c1 = b;

        }


        return "Числа в переменных a, b и c: " + a + ", " + b + ", " + c + "\n" +
                "Возрастающая последовательность: " + a1 + ", " + b1 + ", " + c1;
    }
}
