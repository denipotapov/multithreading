package com.personal.memory;

import annotations.NonThreadSafe;

@NonThreadSafe
public class Inconsistency {

    private int counter;
    private int operationsQty;

    public Inconsistency(int operationsQty) {
        this.operationsQty = operationsQty;
    }

    public Runnable multithreadedIncrement = this::increment;

    public Runnable multithreadedDecrement = this::decrement;

    private void increment() {
        for (int i = 0; i < operationsQty; i++) {
            counter++;
        }
    }

    private void decrement() {
        for (int i = 0; i < operationsQty; i++) {
            counter--;
        }
    }

    public int getCounter() {
        return counter;
    }
}
