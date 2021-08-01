package com.saray.project.multythreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicBroken {

    // для каждого потока должен выдавать новое исключительное значение
    private static volatile int count = 0;

    // можно добавить synchronized и убрать volatile
    public static int nextInt() {
        return count++;
    }

    // второй способ решения проблемы
//    private static final AtomicInteger count = new AtomicInteger();
//
//    public static int nextInt() {
//        return count.getAndIncrement();
//    }

    public static void main(String[] args) throws Exception {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    nextInt();
                }
            });
            thread.start();
            threads.add(thread);
        }

        for (Thread t :
                threads) {
            t.join();
        }

        System.out.println(count);
    }
}

