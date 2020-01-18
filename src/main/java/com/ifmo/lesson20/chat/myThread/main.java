package com.ifmo.lesson20.chat.myThread;

import javax.swing.plaf.metal.MetalBorders;
import java.util.ArrayList;
import java.util.List;

public class main {

    static List<Thread> arr = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = null;
        for (int i = 0; i < 10; i++) {
            thread = new Thread(new myNewTheard());
            thread.start();
            Thread.sleep(1000);
            arr.add(thread);
        }

        Thread.sleep(1000);
        arr.get(0).interrupt();

        System.out.println("exit");
    }



    public static class myNewTheard implements Runnable{

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()){
                System.out.println(Thread.currentThread().getName());
                if(arr.size() > 0 && arr.indexOf(Thread.currentThread()) != 0) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " join.");
                        arr.get(arr.size() - 1).join();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        arr.remove(Thread.currentThread());
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        }
    }

}
