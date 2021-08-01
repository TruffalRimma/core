package com.saray.project.multythreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class CarCond {
    // condition используется для управления взаимодействия между задачами + не хранит информации о состоянии объекта
    // нужна проверка в цикле также как и в wait/notify
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean waxOn = false;

    public void waxed() {
        lock.lock();
        try {
            waxOn = true; // готово к полировке
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void buffed() {
        lock.lock();
        try {
            waxOn = false; // готово к нанесению слоя воска
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void waitForWaxing() throws InterruptedException {
        lock.lock();
        try {
            while (!waxOn) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }
    }

    public void waitForBuffing() throws InterruptedException {
        lock.lock();
        try {
            while (waxOn) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }
    }
}

class WaxOnCond implements Runnable {
    private final CarCond car;

    public WaxOnCond(CarCond c) {
        car = c;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.print("Wax On! ");
                TimeUnit.MILLISECONDS.sleep(2000);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupt!");
        }
        System.out.print("Ending Wax On task... ");
    }
}

class WaxOffCond implements Runnable {
    private final CarCond car;

    public WaxOffCond(CarCond c) {
        car = c;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForWaxing();
                System.out.print("Wax Off! ");
                TimeUnit.MILLISECONDS.sleep(2000);
                car.buffed();
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupt!");
        }
        System.out.print("Ending Wax Off task... ");
    }
}

public class ConditionDemo {
    public static void main(String[] args) throws Exception{
        CarCond carCond = new CarCond();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new WaxOffCond(carCond));
        service.execute(new WaxOnCond(carCond));

        TimeUnit.SECONDS.sleep(20);

        service.shutdownNow();
    }
}
