package com.personal.guardedBlocks;

import com.personal.annotations.ThreadSafe;

import java.util.ArrayList;

@ThreadSafe
public class MessageQueue {

    private boolean empty = true;
    private volatile int queueSize;
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

        queueSize = messages.size() - 1;
        String lastMessage = messages.get(queueSize);
        messages.remove(queueSize);

        if (messages.isEmpty()) {
            empty = true;
        }

        return lastMessage;
    }

    public int getQueueSize() {
        return queueSize;
    }
}
