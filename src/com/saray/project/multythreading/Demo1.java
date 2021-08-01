package com.saray.project.multythreading;

public class Demo1 {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            // если вызвать не start а run, то отдельного потока создано не будет
            // выполнится просто переопределенный метод run в классе
            new HelloThread().start();
        }

        System.out.println("Hello from main");
    }


    private static class HelloThread extends Thread {
        // этот класс при создании экземпляра отпочкуется от метода мейн
        // и будет выполняться в отдельном потоке (параллельно) / в отдельном ядре
        @Override
        public void run() {
            // в классе родителе он не определен
            System.out.println("Hello from " + getName());
        }
    }
}
