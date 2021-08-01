package com.saray.project.multythreading;

/*
ЛОКАЛЬНАЯ ПАМЯТЬ ПОТОКОВ - java.lang.ThreadLocal
механизм, автоматически создающий для одной переменной
несколько блоков памяти - по одному для каждого потока,
использующего объект
 */

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Accessor implements Runnable {
    private final int id;
    public Accessor(int idn) {
        id = idn;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    @Override
    public String toString() {
        return "Accessor{" +
                "id=" + id +
                '}' + ThreadLocalVariableHolder.get();
    }
}

public class ThreadLocalVariableHolder {
    // объекты ThreadLocal обычно хранятся в статических полях
    private static ThreadLocal<Integer> value = new ThreadLocal<>() {
        private Random random = new Random(47);
        protected synchronized Integer initialValue() {
            return random.nextInt(10000);
        }
    };

    // методы не синхронизированы, так как ThreadLocal гарантирует отсутствие ситуации гонки
    public static void increment() {
        value.set(value.get() + 1);
    }

    // при создании объекта к содержимому можно обратиться только через геттеры/сеттеры
    // гет - возвращает копию объекта, связанную с потоком
    // сет - вставляет свой аргумент в объект, хранящийся для потока, и возвращает старый объект,
    // находящийся в хранилище
    public static int get() {return value.get();}

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Accessor(i));
        }

        TimeUnit.SECONDS.sleep(3);
        executorService.shutdown();
    }
}
