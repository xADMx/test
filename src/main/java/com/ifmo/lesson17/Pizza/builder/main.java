package com.ifmo.lesson17.Pizza.builder;

public class main {
    public static void main(String[] args) {
        Pizza pizza = new Pizza.PizzaBuilder("Тесто", "Чедр").build();
    }
}
