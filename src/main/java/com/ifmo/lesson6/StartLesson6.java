package com.ifmo.lesson6;

public class StartLesson6 {

    public static void main(String[] args){
        ArrayList list = new ArrayList();
        list.add("0");
        list.add("1");
        list.add("3");
        list.add("4");
        list.remove(5);
        for (Object obj : list) {
            System.out.println("Сумма: " + obj);
        }

        System.out.println("сумма: " + list.get(4));

    }



}
