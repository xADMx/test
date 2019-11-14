package com.ifmo.lesson12;

public interface Transofrmer<T, R>{
    R transform(T t);

    default boolean valid(T t) {
        try{
            transform(t);
        }catch (Exception ex){
            return false;
        }
        return true;
    }
}
