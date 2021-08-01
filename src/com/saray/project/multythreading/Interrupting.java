package com.saray.project.multythreading;

/*
ПРЕРЫВАНИЕ ЗАБЛОКИРОВАННОГО ПОТОКА
каждая задача представляет собой разновидность блокировки
 */

import java.io.IOException;
import java.io.InputStream;;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// пример прерываемой блокировки
class SleepBlocked implements Runnable {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Interrupted in Sleep");
        }

        System.out.println("SleepBlocked.run()");
    }
}

// непрерываемая блокировка
class IOBlocked implements Runnable {
    private InputStream io;

    public IOBlocked(InputStream io) {
        this.io = io;
    }

    @Override
    public void run() {
        try {
            System.out.println("Waiting for read()");
            io.read();
        } catch (IOException e) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted in IO");
            } else {
                System.out.println("Runtime in IO");
            }
        }

        System.out.println("IOBlocked.run()");
    }
}

// непрерываемая блокировка
// задачу нельзя прервать interrupt()
class SynchronizedBlocked implements Runnable {

    // конструктор создает экземпляр анонимного класса, который получает блокировку методом f()
    public SynchronizedBlocked() {
        new Thread() {
            @Override
            public void run() {
                f(); // блокировка устанавливается этим потоком
            }
        }.start();
    }

    public synchronized void f() {
        while (true) { // блокировка никогда не снимается, так как метод никогда не возвращает управление
            Thread.yield();
        }
    }

    @Override
    public void run() {
        System.out.println("Trying to call f()");
        // пытается вызвать метод и блокируется в ожидании снятия блокировки
        f();
        System.out.println("SynchronizedBlocked.run()");
    }
}

public class Interrupting {
    private static ExecutorService service = Executors.newCachedThreadPool();

    static void test(Runnable r) throws InterruptedException {
        Future<?> future = service.submit(r);
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("Interrupting " + r.getClass().getName());
        // прервать, если выполняется
        future.cancel(true);
        System.out.println("Interrupt sent to " + r.getClass().getName());
    }

    public static void main(String[] args) throws Exception {
        test(new SleepBlocked());
        test(new IOBlocked(System.in)); // можно прервать закрытием ресурсов
        test(new SynchronizedBlocked());
        TimeUnit.SECONDS.sleep(3);

        // так как два последних прерывания завершились неудачей
        // непрерываемые блокировки
        System.out.println("Aborting with System.exit(0)");
        System.exit(0);
    }
}
