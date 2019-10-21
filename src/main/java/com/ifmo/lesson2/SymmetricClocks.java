package com.ifmo.lesson2;

public class SymmetricClocks {
    /*
    Электронные часы показывают время в формате от 00:00 до 23:59. Подсчитать сколько
    раз за сутки случается так, что слева от двоеточия показывается симметричная комбинация
    для той, что справа от двоеточия (например, 02:20, 11:11 или 15:51)
     */
    public static void main(String[] args) {
        System.out.println(symmetricTimes());
    }

    public static int symmetricTimes() {
        // TODO implement
        int count = 0;
        for (int i = 0; i < 24; i++) {
            int leftH = i / 10;
            int rightH = i % 10;
            for (int j = 0; j < 60; j++) {
                int leftM = j / 10;
                int rightM = j % 10;
                if (rightH == leftM & leftH == rightM) {
                    count++;
                    System.out.println(leftH + "" + rightH +":"+leftM +""+rightM);
                }

            }
        }
        return count;
    }
}
