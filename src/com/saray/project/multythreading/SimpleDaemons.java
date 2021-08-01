package com.saray.project.multythreading;

// Потоки-демоны не препятствуют завершению работы программы

import java.util.concurrent.TimeUnit;

public class SimpleDaemons implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.SECONDS.sleep(1L);
                System.out.println(Thread.currentThread() + " " + this);
            }

        } catch (InterruptedException e) {
            System.out.println("error");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            // режим демона устанавливается ПЕРЕД запуском потока
            daemon.setDaemon(true);
            daemon.start();
        }

        System.out.println("All daemons started");

        // в зависимости от времени задержки мы увидим в консоли разное количество потоков-демонов
        TimeUnit.MILLISECONDS.sleep(5000);

        // при завершении метода мейн - завершится и вывод потоков-демонов
        // может и ничего и не выводится
    }
}

