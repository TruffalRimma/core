package com.saray.project.multythreading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// контейнер может содержать фиксированное число элементов любого типа
public class ReaderWriterList<T> {
    private final ArrayList<T> lockedList;

    // Make the ordering fair
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    // конструктору передается нужный размер списка и исходный объект для заполнения списка
    public ReaderWriterList(int size, T initialValue) {
        // immutable list with initialValue * size content
        lockedList = new ArrayList<>(Collections.nCopies(size, initialValue));
    }

    // получает блокировку записи
    public void set(int index, T element) {
        lock.writeLock().lock();
        try {
            lockedList.set(index, element);
        } finally {
            lock.writeLock().unlock();
        }
    }

    // получает блокировку чтения
    public T get(int index) {
        lock.readLock().lock();
        try {
            // few threads can read
            if (lock.getReadLockCount() > 1)
                System.out.print("\nlockcount " + lock.getReadLockCount());
            return lockedList.get(index);
        } finally {
            lock.readLock().unlock();
        }
    }

    public static void main(String[] args) throws Exception {
        // задач записи намного меньше чем задач чтения
        new ReadWriterListTest(30, 2);
    }
}

class ReadWriterListTest {
    ExecutorService service = Executors.newCachedThreadPool();

    private static final int SIZE = 100;
    private static final Random random = new Random(47);
    // initialize by 0s * 100 elements
    private final ReaderWriterList<Integer> list = new ReaderWriterList<>(SIZE, 0);

    private class Writer implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 20; i++) {
                    list.set(i, random.nextInt());
                    TimeUnit.SECONDS.sleep(5);
                }
            } catch (InterruptedException ignored) {
            }

            System.out.println("Writing finished");
            service.shutdownNow();
        }
    }

    private class Reader implements Runnable {
        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    for (int i = 0; i < SIZE; i++) {
                        System.out.print(list.get(i) + " ");
                        TimeUnit.SECONDS.sleep(5);
                    }
                }
            } catch (InterruptedException ignored) {
            }
        }
    }

    public ReadWriterListTest(int readers, int writers) {
        for (int i = 0; i < readers; i++) {
            service.execute(new Reader());
        }
        for (int i = 0; i < writers; i++) {
            service.execute(new Writer());
        }
    }
}
