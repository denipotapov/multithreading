package com.personal;


import com.personal.guardedBlocks.Consumer;
import com.personal.guardedBlocks.MessageQueue;
import com.personal.guardedBlocks.Producer;
import com.personal.memory.ConsistencySynchMethods;
import com.personal.memory.ConsistencySynchStatement;
import com.personal.memory.Inconsistency;

public class App {

    public static void main(String[] args) throws InterruptedException {

        // Simple example of threads memory inconsistency and inference.
        // One thread consumes memory of another thread and produces unexpected result.
        Inconsistency inconsistency = new Inconsistency(1000);
        while (inconsistency.getCounter() == 0) {
            Thread t1 = new Thread(inconsistency.multithreadedIncrement);
            Thread t2 = new Thread(inconsistency.multithreadedDecrement);
            t1.start();
            t2.start();

            t1.join();
            t2.join();
            if (inconsistency.getCounter() != 0) {
                break;
            }
            System.out.println("Result is " + inconsistency.getCounter() + ". Continuing");
        }
        System.out.println("Inconsistency result: " + inconsistency.getCounter());

        // Simple example of threads memory consistency. Methods are synchronised fully.
        // Result must be the same all time.
        ConsistencySynchMethods consistencySynchMethods = new ConsistencySynchMethods(1000);
        Thread t1 = new Thread(consistencySynchMethods.multithreadedIncrement);
        Thread t2 = new Thread(consistencySynchMethods.multithreadedDecrement);
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("ConsistencySynchMethods result: " + consistencySynchMethods.getCounter());

        // Simple example of threads memory consistency. Only statements are synchronised.
        // Result must be the same all time.
        ConsistencySynchStatement consistencySynchStatement = new ConsistencySynchStatement(1000);
        Thread t3 = new Thread(consistencySynchStatement.multithreadedIncrement);
        Thread t4 = new Thread(consistencySynchStatement.multithreadedDecrement);
        t3.start();
        t4.start();

        t3.join();
        t4.join();
        System.out.println("ConsistencySynchStatement result: " + consistencySynchStatement.getCounter());

        //Guarded blocks example. Thread producer puts data into message queue and notifies other
        //threads about the fact, that the queue is not empty. If some consumer was waiting by the method
        //wait(), then it continues it's job.
        String[] messages = {"Alalalalal1", "Alalalalal2", "Alalalalal3", "Alalalalal4", "Alalalalal5"};
        MessageQueue messageQueue = new MessageQueue();
        new Thread(new Consumer(messageQueue)).start();
        new Thread(new Producer(messageQueue).setMessages(messages)).start();



    }

}
