package com.ifmo.lesson6;

import java.util.Iterator;

public class StartLesson6 {

    public static void main(String[] args){
        ArrayList list = new ArrayList();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
//        list.remove(3);

        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println("Сумма: " + iterator.next());
        }

    }



}
