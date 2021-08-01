package com.saray.project.chapter2;
/**
 * Глава 2. Упражнения 4, 5, 6, 7.
 * 4. (1) Найдите фрагмент кода с классом DataOnly и сделайте из него программу, пригодную для компиляции и запуска.
 * 5. (1) Измените упражнение 4 так, чтобы данным класса DataOnly присваивались
 * значения, а затем распечатайте их в методе main().
 * 6. (2) Напишите программу, включающую метод storage(), приведенный ранее в этой
 * главе.
 * 7. (1) Превратите фрагменты кода с классом lncrementable в работающую программу.
 */
public class Task4567 {
    int i = 7;
    double d = 0.1d;
    boolean b = true;

    public static void main(String[] args) {
        Task4567 task456 = new Task4567();
        System.out.println(task456);
        System.out.println(task456.storage("Hello, World!"));
        System.out.println(task456.storage("Bye, World!"));

        Incrementable.increment();
        System.out.println(System.nanoTime());
    }

    @Override
    public String toString() {
        return "Task456 {" +
                "i=" + i +
                ", d=" + d +
                ", b=" + b +
                '}';
    }

    /*
    Метод указывает, сколько байтов потребуется для хранения данных определенной строки
     */
    int storage(String s) {
        return s.length() * 2;
    }
}

class StaticTest {
    static int i = 47;
}

class Incrementable {
    static void increment() {
        StaticTest.i++;
    }
}
