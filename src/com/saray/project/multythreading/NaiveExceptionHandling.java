package com.saray.project.multythreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/*
метод ран не может генерировать никаких проверяемых исключений,
НО может быть прерван непроверяемым исключением - тогда поток уничтожается
 */
public class NaiveExceptionHandling {
    public static void main(String[] args) {
        try {
            ExecutorService service = Executors.newCachedThreadPool();
            service.execute(new ExceptionThread());
        } catch (RuntimeException e) {
            // IT DOESNT WORK !!!
            System.out.println("Exception has been handled!");
        }
    }
}

class ExceptionThread implements Runnable {
    public void run() {
        throw new RuntimeException();
    }

//    public static void main(String[] args) {
//        ExecutorService service = Executors.newCachedThreadPool();
//        service.execute(new ExceptionThread());
//    }
}