package com.personal.guardedBlocks;

import com.personal.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class MessageQueue {

    private boolean empty = true;
    private AtomicInteger queueSize = new AtomicInteger(0);
    private ArrayList<String> messages;

    public MessageQueue() {
        messages = new ArrayList<>();
    }

    public synchronized void put(String message) {
        messages.add(message);
        empty = false;
        notifyAll();
    }

    public synchronized String getLastMessage() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        queueSize.set(messages.size() - 1);
        String lastMessage = messages.get(queueSize.get());
        messages.remove(queueSize.get());

        if (messages.isEmpty()) {
            empty = true;
        }

        return lastMessage;
    }

    public int getQueueSize() {
        return queueSize.get();
    }
}
