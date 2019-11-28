package com.ifmo.lesson17.Pizza.builder;

public class Pizza {
    private final String dought;
    private final String chees;
    private final int catchup;
    private final int tomatos;
    private final int pepperoni;

    public static class PizzaBuilder{
        private final String dought;
        private final String chees;
        private int catchup;
        private int tomatos;
        private int pepperoni;

        public PizzaBuilder setCatchup(int Catchup) {
            this.catchup = catchup;
            return this;
        }

        public PizzaBuilder setTomatos(int tomatos) {
            this.tomatos = tomatos;
            return this;
        }

        public PizzaBuilder(String dought, String chees) {
            this.dought = dought;
            this.chees = chees;
        }

        public PizzaBuilder setPepperoni(int pepperoni) {
            this.pepperoni = pepperoni;
            return this;
        }

        public Pizza build(){
            return new Pizza(this);
        }

    }

    private Pizza(PizzaBuilder pizzaBuilder) {
        this.dought = pizzaBuilder.dought;
        this.chees = pizzaBuilder.chees;
        this.catchup = pizzaBuilder.catchup;
        this.tomatos = pizzaBuilder.tomatos;
        this.pepperoni = pizzaBuilder.pepperoni;
    }
}
