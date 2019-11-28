package com.ifmo.lesson18.Observer;

import java.util.Random;

public class main {
    public static void main(String[] args) {
        Obser obser = new Obser();
        obser.addSub(new ViewGreen());
        obser.addSub(new ViewYellow());
        obser.addSub(new ViewRed());

        Random rnd = new Random();

        for (int i = 0; i < 10 ; i++) {
            obser.newTemper(rnd.nextInt(150) + 10);
        }
    }
}
