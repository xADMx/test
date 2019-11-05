package com.ifmo.lesson9;



public class StartLesson9 {

    public static void main(String[] args){
        /*ArrayList<String> list = new ArrayList();
        list.add("0");
        list.add("1");
        list.add("3");
        list.add("4");
        list.remove(5);
        for (Object obj : list) {
            System.out.println("Сумма: " + obj);
        }

        System.out.println("сумма: " + list.get(4));*/
        DblNumberAppendable dblNum = new DblNumberAppendable(new ArithmeticOperation<Double>() {
            @Override
            public Double operation(Double v1, Double v2) {
                return v1 * v2;
            }
        });

        System.out.println(dblNum.append(2.0).append(3.0).append(4.0).value());

    }



}
