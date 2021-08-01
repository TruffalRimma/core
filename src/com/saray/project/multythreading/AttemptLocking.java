package com.saray.project.multythreading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/*
Объекты Lock позволяеют отказаться
от получения блокировки по тайм-айту
 */
public class AttemptLocking {

    // дает возможность попытаться получить блокировку с неудачным исходом
    // чтобы, если блокировка уже была кем-то захвачена, ваша программа могла
    // заняться чем-то другим вместо ожидания ее освобождения
    private ReentrantLock reentrantLock = new ReentrantLock();

    public void untimed() {
        boolean captured = reentrantLock.tryLock();
        try {
            System.out.println("tryLock(): " + captured);
        } finally {
            if (captured)
                reentrantLock.unlock();
        }
    }

    // ждем 2 секунды
    public void timed() {
        boolean captured = false;

        try {
            captured = reentrantLock.tryLock(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }

        try {
            System.out.println("tryLock(2, TimeUnit.SECONDS): " + captured);
        } finally {
            if (captured)
                reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        final AttemptLocking al = new AttemptLocking();
        al.untimed();
        al.timed();

        // отдельный объект потока создается в виде анонимного класса
        // и получает блокировку, чтобы у методов был ресурс, за который они могли бы конкурировать
        new Thread() {
            {setDaemon(true);}

            @Override
            public void run() {
                al.reentrantLock.lock();
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("acquired");
            }
        }.start();
        Thread.yield();
        al.untimed();
        al.timed();
    }
}
