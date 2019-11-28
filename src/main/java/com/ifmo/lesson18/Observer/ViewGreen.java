package com.ifmo.lesson18.Observer;

public class ViewGreen implements IView {

    public ViewGreen() {
    }

    public void viewMsg(int temper) {
        if(temper > 70)
            System.out.println("Green: " + temper);
    }
}
