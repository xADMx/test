package com.ifmo.lesson5;

public class StartLesson5 {

    public static void main(String[] args){
        LinkedList  list = new LinkedList();
        list.add(new Oval(0.5, 10));
        list.add(new Square(5));
        list.add(new Rectangle(5, 10));
        list.add(new Triangle(6, 10, 8));
        System.out.println("Сумма: " + totalArea(list));
    }


    public static double totalArea(LinkedList shapes) {
        double sum = 0;
        for (int i = 0; i < shapes.getCount(); i++) {
            sum += shapes.get(i).area();
        }
        return sum;
    }
}
