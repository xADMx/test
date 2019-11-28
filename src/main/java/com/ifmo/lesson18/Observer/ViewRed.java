package com.ifmo.lesson18.Observer;

public class ViewRed implements IView {
    public ViewRed() {

    }

    @Override
    public void viewMsg(int temper) {
        if(temper > 130)
            System.out.println("Red: " + temper);
    }
}
