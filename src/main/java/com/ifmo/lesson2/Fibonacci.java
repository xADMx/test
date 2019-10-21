package com.ifmo.lesson2;

public class Fibonacci {
    /*
     Выведите на экран первые 11 членов последовательности Фибоначчи. Напоминаем, что
     первый и второй члены последовательности равны единицам, а каждый следующий — сумме
     двух предыдущих.
     */
    public static void main(String[] args) {
        // TODO implement
        int first = 1;
        int second = 1;
        System.out.println(first);
        System.out.println(second);
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                first += second;
                System.out.println(first);
            } else {
                second += first;
                System.out.println(second);
            }
        }
    }
}
