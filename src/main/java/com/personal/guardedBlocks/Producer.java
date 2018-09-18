package com.personal.guardedBlocks;

public class Producer implements Runnable {

    private String[] messages;
    private MessageQueue messageQueue;

    public Producer (MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    public Producer setMessages(String[] messages) {
        this.messages = messages;
        return this;
    }

    @Override
    public void run() {
        for (String message : messages) {
            messageQueue.put(message);
        }
    }
}
