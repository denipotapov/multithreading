package com.personal.memory;

import com.personal.annotations.ThreadSafe;

@ThreadSafe
public class ConsistencySynchMethods {

    private int counter;
    private int operationsQty;

    public ConsistencySynchMethods(int operationsQty) {
        this.operationsQty = operationsQty;
    }

    public Runnable multithreadedIncrement = this::increment;

    public Runnable multithreadedDecrement = this::decrement;

    private synchronized void increment() {
        for (int i = 0; i < operationsQty; i++) {
            counter++;
        }
    }

    private synchronized void decrement() {
        for (int i = 0; i < operationsQty; i++) {
            counter--;
        }
    }

    public synchronized int getCounter() {
        return counter;
    }
}
