package com.ifmo.lesson2;

import java.util.Random;

public class IsInInterval {
    /*
     Создать программу, которая будет проверять попало ли случайно выбранное из отрезка
    [5;155] целое число в интервал (25;100) и сообщать результат на экран.Примеры работы
    программы:
    Число 113 не содержится в интервале (25,100) Число 72 содержится в интервале (25,100) 
    Число 25 не содержится в интервале (25,100) Число 155 не содержится в интервале (25,100) 
     */
    public static void main(String[] args) {
        int rnd = randomInt();

        String inInterval = isInInterval(rnd);
        System.out.println(inInterval);
        // TODO implement
    }

    public static int randomInt() {
        Random rnd = new Random();
        return rnd.nextInt(151) + 5;
    }

    public static String isInInterval(int rnd) {
        // TODO implement
        return (rnd > 25 & rnd < 100) ? "Число " + rnd + " содержится в интервале (25,100)" : "Число " + rnd + " не содержится в интервале (25,100)";
    }

}
