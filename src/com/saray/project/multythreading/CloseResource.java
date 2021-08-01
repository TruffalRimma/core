package com.saray.project.multythreading;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
ПРЕРЫВАНИЕ ЗАБЛОКИРОВАННОЙ ЗАДАЧИ ПОСРЕДСТВОМ
ЗАКРЫТИЯ РЕСУРСА, ПО КОТОРОМУ ОНА БЛОКИРУЕТСЯ
ВВОД / ВЫВОД
 */
public class CloseResource {
    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newCachedThreadPool();

        ServerSocket server = new ServerSocket(8080);
        InputStream socketInput = new Socket("localhost", 8080).getInputStream();

        service.execute(new IOBlocked(socketInput));
        service.execute(new IOBlocked(System.in));

        TimeUnit.MILLISECONDS.sleep(100);

        System.out.println("Shutting down all threads");
        service.shutdown();

        TimeUnit.SECONDS.sleep(1);

        System.out.println("Closing " + socketInput.getClass().getName());
        socketInput.close(); // освобождение заблокированного потока

        TimeUnit.SECONDS.sleep(1);

        System.out.println("Closing " + System.in.getClass().getName());
        System.in.close(); // освобождение заблокированного потока
    }
}
