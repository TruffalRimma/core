package com.saray.project.multythreading;

import java.util.ArrayList;
import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        ArrayList<Future<String>> results = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            results.add(executorService.submit(new TaskWithResult(i)));
        }

        for (Future<String> fs :
                results) {
            try {
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                System.out.println(e);
                return;
            } catch (ExecutionException e1) {
                System.out.println(e1);
            } finally {
                executorService.shutdown();
            }
        }
    }
}

/*
Если вы хотите возращвть значение при завершении задачи, то реализуйте Callable
 */
class TaskWithResult implements Callable<String> {

    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "result of TaskWithResult" + id;
    }

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Runnable task = () -> {
            synchronized (lock) {
                System.out.println("thread");
            }
        };
        Thread th1 = new Thread(task);
        th1.start();
        synchronized (lock) {
            for (int i = 0; i < 8; i++) {
                Thread.sleep(1000);
                System.out.print("  " + i + " ");
                System.out.print(th1.getState()); // заблокирован и не может продолжать работу, пока лок не вернут.
            }
            System.out.println(" ...");
        }
    }
}
