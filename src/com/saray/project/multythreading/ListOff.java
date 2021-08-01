package com.saray.project.multythreading;

// ОБРАТНЫЙ ОТСЧЕТ ПЕРЕД ЗАПУСКОМ

public class ListOff implements Runnable {

    protected int countDown = 10; // default
    private static int taskCount = 0;
    private final int id = taskCount++; // различает экзмепляры задачи

    public ListOff() {
        System.out.println("" + Thread.currentThread().getState() + id + "created");
    }

    public ListOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown : "Listoff!") + "), ";
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println(status());
            System.out.println("Before getState " + id);
            System.out.println(Thread.currentThread().getState() + " " + id);
            System.out.println("After getState " + id);
            Thread.yield();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MainThread {
    public static void main(String[] args) {
        ListOff launch = new ListOff();
        launch.run();
    }
}
