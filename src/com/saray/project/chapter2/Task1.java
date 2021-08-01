package com.saray.project.chapter2;

/**
 * Глава 2. Упражнение 1.
 * Вывести непроинициализированные значения в консоль, чтобы убедиться в том,
 * что Java выполняет инициализацию по умолчанию
 */
public class Task1 {

    static int x;
    static char c;

    public static void main(String[] args) {
        otherMethod(x, c);
    }

    static void otherMethod(int x, char c){
        System.out.println(x);
        System.out.println(c);
    }
}
