package com.ifmo.lesson18.Observer;

public class ViewYellow implements IView {
    public ViewYellow() {
    }

    @Override
    public void viewMsg(int temper) {
        if(temper > 110)
            System.out.println("Yellow: " + temper);
    }
}
