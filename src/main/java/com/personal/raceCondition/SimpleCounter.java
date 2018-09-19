package com.personal.raceCondition;

import com.personal.annotations.NonThreadSafe;

@NonThreadSafe
public class SimpleCounter {

    private int counter;

    public void increment() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleCounter sc = new SimpleCounter();

        Runnable r1 = () -> {
            for (int i = 0; i < 1000; i++) {
                sc.increment();
            }
        };
        Runnable r2 = () -> {
            for (int i = 0; i < 1000; i++) {
                sc.increment();
            }
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(sc.getCounter());
    }
}
