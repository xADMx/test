package com.ifmo.lesson6;

public class StartLesson6 {

    public static void main(String[] args){
        LinkedList list = new LinkedList();


        list.remove(3);
        for (Object obj : list) {
            System.out.println("Сумма: " + obj);
        }

    }



}
