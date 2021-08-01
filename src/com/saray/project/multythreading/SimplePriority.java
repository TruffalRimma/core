package com.saray.project.multythreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimplePriority implements Runnable {

    private int countDown;
    private volatile double d; // no optimization
    private int priority;

    public SimplePriority(int priority) {
        this.priority = priority;
    }

    public String toString() {
        return Thread.currentThread() + ": " + countDown;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);

        while (true) {
            // Высокозатратная прерываемая операция
            for (int i = 0; i < 100000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if(i % 1000 == 0) {
                    Thread.yield();
                }
            }
            System.out.println(this);
            if (countDown-- == 0) return;
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            executorService.execute(new SimplePriority(Thread.MIN_PRIORITY));
        }

        executorService.execute(new SimplePriority(Thread.MAX_PRIORITY));
        executorService.shutdown();
    }
}
