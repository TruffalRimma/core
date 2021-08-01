package com.saray.project.multythreading;

/*
Мы создали простой класс ThreadExample.
Его задача — вывести на экран сообщение о начале работы,
потом уснуть на 5 секунд и в конце сообщить о завершении работы.
Ничего сложного.
 */
public class ThreadExample extends Thread {

    @Override
    public void run() {

        System.out.println("Начало работы потока " + getName());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Поток " + getName() +  " завершил работу.");
    }
}

/*
C помощью метода join() мы успешно управляем последовательностью выполнения потоков.
Если ты вспомнишь начало темы, этим занимался планировщик потоков.
Он запускал их на свое усмотрение: каждый раз по-разному.
 */
class Main {

    public static void main(String[] args) throws InterruptedException {

        ThreadExample t1 = new ThreadExample();
        ThreadExample t2 = new ThreadExample();

        t1.start();


 /*Второй поток t2 начнет выполнение только после того, как будет завершен
       (или бросит исключение) первый поток - t1*/
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();

        //Главный поток продолжит работу только после того, как t1 и t2 завершат работу
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Все потоки закончили работу, программа завершена");

    }
}
