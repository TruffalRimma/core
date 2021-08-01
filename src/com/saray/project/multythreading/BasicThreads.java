package com.saray.project.multythreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BasicThreads {
    public static void main(String[] args) {
        // поток еще не запущен на выполнение
        Thread t = new Thread(new ListOff());

        // выполняет необходимую инициализацию потока
        t.start();
        // потом вызывает метод run
        System.out.println("Waiting for ListOff()");
    }
}

class MoreBasicThreads {
    public static void main(String[] args) {
        // переключение потоков обеспечивается автоматически ПЛАНИРОВЩИКОМ ПОТОКОВ (механизм квантования)
        for(int i = 0; i < 5; i++) {
            new Thread(new ListOff()).start();
            System.out.println("Waiting for ListOff()");
        }
    }
}

class CachedThreadPool {
    public static void main(String[] args) {
        // Объект Executor позволяет управлять выполнением асинхронных задач
        // без явного управления жизненным циклом потоков
        ExecutorService executorService = Executors.newCachedThreadPool();
        // создание потока для каждой задачи
        for (int i = 0; i < 5; i++) {
            // объект ExecutorService - Executor с жизненным циклом службы
            // умеет строить контекст для выполнения объектов Runnable
            executorService.execute(new ListOff());
        }
        // предотвращает отправку новых задач Executor
        executorService.shutdown();
    }
}

class FixedThreadPool {
    public static void main(String[] args) {
        // Высокозатратное создание потоков выполняется один раз в самом начале
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // создание потока для каждой задачи
//        for (int i = 0; i < 5; i++) {
//            // объект ExecutorService - Executor с жизненным циклом службы
//            // умеет строить контекст для выполнения объектов Runnable
//            executorService.execute(new ListOff());
//        }
        executorService.execute(new ListOff());
        // предотвращает отправку новых задач Executor
        executorService.shutdown();
    }
}

class SingleThreadExecutor {
    // оследовательное выполнение задач снимает необходимость в синхронизации доступа к объектам
    public static void main(String[] args) {
        // последовательное выполнение потоков (ставятся в очередь)
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new ListOff(3));
        }
        executorService.shutdown();
    }
}