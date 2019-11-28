package com.ifmo.lesson18.Observer;

import java.util.ArrayList;
import java.util.List;

public class Obser {

    private List<IView> listerner = new ArrayList<>();

    public Obser(List<IView> listerner) {
        this.listerner = listerner;
    }

    public Obser() {

    }

    public void addSub(IView view){
        listerner.add(view);
    }

    public void removeSub(IView view){
        listerner.remove(view);
    }

    public void newTemper(int temper){

        System.out.println("Новые показания: " + temper);

        send(temper);
    }

    private void send(int temper){
        for(IView listerne : listerner)
            listerne.viewMsg(temper);
    }

}
