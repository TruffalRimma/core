package com.saray.project.multythreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Car {
    private boolean waxOn = false;

    public synchronized void waxed() {
        waxOn = true; // готово к полировке
        notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false; // готово к нанесению слоя воска
        notifyAll();
    }

    public synchronized void waitForWaxing() throws InterruptedException {
        while (!waxOn) wait();
    }

    public synchronized void waitForBuffing() throws InterruptedException {
        while (waxOn) wait();
    }
}

class WaxOn implements Runnable {
    private final Car car;
    public WaxOn(Car c) {
        car = c;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                System.out.print("Wax On! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            } catch (InterruptedException e) {
                System.out.println("Interrupt!");
            }
            System.out.print("Ending Wax On task... ");
        }
    }
}

class WaxOff implements Runnable {
    private final Car car;
    public WaxOff(Car c) {
        car = c;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                car.waitForWaxing();
                System.out.print("Wax Off! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            } catch (InterruptedException e) {
                System.out.println("Interrupt!");
                System.exit(0);
            }
            System.out.print("Ending Wax Off task... ");
        }
    }
}

public class WaxOMatic {
    public static void main(String[] args) throws Exception {
        Car car = new Car();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new WaxOff(car));
        service.execute(new WaxOn(car));

        TimeUnit.SECONDS.sleep(2);

        service.shutdownNow();
    }
}
