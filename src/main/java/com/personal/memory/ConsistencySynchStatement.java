package com.personal.memory;

import annotations.ThreadSafe;

@ThreadSafe
public class ConsistencySynchStatement {

    private int counter;
    private int operationsQty;

    public ConsistencySynchStatement(int operationsQty) {
        this.operationsQty = operationsQty;
    }

    public Runnable multithreadedIncrement = this::increment;

    public Runnable multithreadedDecrement = this::decrement;

    private void increment() {
        for (int i = 0; i < operationsQty; i++) {
            synchronized (this) {
                counter++;
            }
        }
    }

    private void decrement() {
        for (int i = 0; i < operationsQty; i++) {
            synchronized (this) {
                counter--;
            }
        }
    }

    public synchronized int getCounter() {
        return counter;
    }

}
