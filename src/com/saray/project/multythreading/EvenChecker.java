package com.saray.project.multythreading;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

abstract class IntGenerator {
    // атомарный флаг - простые опреации (присваивание и возвращение значения)
    // не прерываются при выполнении
    // не увидишь поле в промежуточном состоянии в середине простых операций
    private volatile boolean canceled = false;

    // генерация следующего значения
    public abstract int next();

    // отмена
    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }
}

// тестирование IntGenerator
public class EvenChecker implements Runnable {

    private IntGenerator intGenerator;
    private final int id;

    public EvenChecker(IntGenerator g, int id) {
        intGenerator = g;
        this.id = id;
    }

    @Override
    public void run() {
        while (!intGenerator.isCanceled()) {
            int val = intGenerator.next();
            if (val % 2 != 0) {
                System.out.println(val + " not even(четное)");
                intGenerator.cancel();
            }
        }
    }

    public static void test(IntGenerator g, int count) {
        System.out.println("Press Ctrl-C to exit");

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            executorService.execute(new EvenChecker(g, i));
        }
        executorService.shutdown();
    }

    public static void test(IntGenerator g) {
        test(g, 10);
    }
}

class EvenGenerator extends IntGenerator {
    private int currentValue = 0;

    // по идее нечетных чисел по итогу быть не должно
    // но выполнение неатомарных операций без защиты в многопоточности НЕБЕЗОПАСНО
    @Override
    public int next() {
        ++currentValue;
        Thread.yield();
        ++currentValue;
        return currentValue;
    }

    public static void main(String[] args) {
        // возникает состояние гонки
        EvenChecker.test(new LockEvenGenerator());
    }
}

class SynchronizedEvenGenerator extends IntGenerator {
    private int currentValue = 0;

    // MUTEX - MUTUAL EXCLUSION
    @Override
    public synchronized int next() {
        ++currentValue;
        Thread.yield();
        ++currentValue;
        return currentValue;
    }

    public static void main(String[] args) {
        // возникает состояние гонки
        EvenChecker.test(new LockEvenGenerator());
    }
}

class LockEvenGenerator extends IntGenerator {
    private int currentValue = 0;
    private Lock lock = new ReentrantLock();

    // MUTEX - MUTUAL EXCLUSION
    @Override
    public int next() {
        lock.lock();
        try {
            ++currentValue;
            Thread.yield();
            ++currentValue;
            return currentValue;
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        // возникает состояние гонки
        EvenChecker.test(new LockEvenGenerator());
    }
}