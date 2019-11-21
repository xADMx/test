package com.ifmo.lesson17.car;

public abstract class AFactory {

    public static AFactory getFactory(String conrtyCode){
        switch (conrtyCode){
            case "RUS":
                return new RUSFactory();
            case "US":
                return new CryslerFactory();
            case "JP":
                return new JPFactory();
        }
        return null;
    }

    public abstract Car getCar();
}
