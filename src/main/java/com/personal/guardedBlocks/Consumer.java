package com.personal.guardedBlocks;

public class Consumer implements Runnable {

    private MessageQueue messageQueue;

    public Consumer(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(messageQueue.getLastMessage());
        }
    }
}
