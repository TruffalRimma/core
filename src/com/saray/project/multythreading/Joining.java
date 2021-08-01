package com.saray.project.multythreading;

// тип потока который приостанавливается на время, указанное в его конструкторе
class Sleeper extends Thread {
    private int duration;

    public Sleeper(String name, int sleepTime) {
        super(name);
        duration = sleepTime;
        start();
        System.out.println("Sleeper started");
    }

    public void run() {
        try {
            // может закончится при истечении времени задержки, но может и прерваться
            // ПОТОК В СОСТОЯНИИ СНА МОЖНО ПРЕРВАТЬ
            sleep(duration);
        } catch (InterruptedException e){
            // флаг interrupted сбрасывается при обработке исключения
            System.out.println(getName() + " was interrupted. " +
                    "isInterrupted(): " + isInterrupted());
            return;
        }
        System.out.println(getName() + " has awakened!");
    }
}

// поток, котрый ожидает пробуждения потока Sleeper, вызывая для последнего метод join()
class Joiner extends Thread {
    private Sleeper sleeper;

    public Joiner (String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }

    public void run() {
        try {
            // Joiner останавливается, пока слипер не поспит
            sleeper.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println(getName() + " join completed");
    }
}

public class Joining {
    public static void main(String[] args) {
        Sleeper
                sleeper = new Sleeper("Sleepy", 1500),
                grumpy = new Sleeper("Grumpy", 1500);
        Joiner
                dopey = new Joiner("Dopey", sleeper),
                doc = new Joiner("doc", grumpy);
        grumpy.interrupt();
    }
}
