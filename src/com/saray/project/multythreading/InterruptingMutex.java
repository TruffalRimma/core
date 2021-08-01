package com.saray.project.multythreading;

// ПРЕРЫВАНИЕ ЗАДАЧИ, ЗАБЛОКИРОВАННОЙ ПО LOCK

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BlockedMutex {
    private Lock lock = new ReentrantLock();
    public BlockedMutex() {
        lock.lock();
    }

    public void f() {
        try {
            lock.lockInterruptibly();
            System.out.println("lock in f()");
        } catch (InterruptedException e) {
            System.out.println("InterruptedException from lock in f");
        }
    }
}

class Blocked2 implements Runnable {
    BlockedMutex blockedMutex = new BlockedMutex();

    @Override
    public void run() {
        System.out.println("Waiting for f in blocked");
        blockedMutex.f();
        System.out.println("Broken out of blocked call");
    }
}

public class InterruptingMutex {
    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new Blocked2());
        t.start();

        TimeUnit.SECONDS.sleep(1);

        System.out.println("t.inter");
        t.interrupt();
    }
}
