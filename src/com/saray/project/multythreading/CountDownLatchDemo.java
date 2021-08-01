package com.saray.project.multythreading;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        // начальное состояние счетчика 10
        CountDownLatch count = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            // создаем 10 потоков и всем раздаем по счетчику (один и тот же)
            new Demo(count).start();
        }
    }

    private static class Demo extends Thread {
        private final CountDownLatch count;

        private Demo(CountDownLatch count) {
            this.count = count;
        }

        @Override
        public void run() {
            try {
                runUnsafe();
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted");
            }
        }

        // все потоки сначала инициализируются, а потом резко одновременно начинают работать
        private void runUnsafe() throws InterruptedException {
            Thread.sleep((long) (Math.random() * 10000L));

            System.out.println(getName() + " finished initialization");

            // уменьшаем счетчик и ждем остальных
            count.countDown();
            count.await();

            System.out.println(getName() + " entered main phase");

            Thread.sleep((long) (Math.random() * 10000L));
        }
    }
}
