package com.saray.project.chapter7;

class Poppet {
    private int i;

    Poppet(int ii) {
        i = ii;
    }
}

public class BlankFinal {
    // Инициализированная константа
    private final int i = 0;

    // Пустая константа
    private final int j;

    // Пустая константа-ссылка
    private final Poppet p;

    // Пустые константы НЕОБХОДИМО инициализировать в каждом из конструкторов
    public BlankFinal() {
        // Инициализация пустой константы
        j = 1;

        // Инициализация пустой неизменной ссылки
        p = new Poppet(1);
    }

    public BlankFinal(int x) {
        // Инициализация пустой константы
        j = x;

        // Инициализация пустой неизменной ссылки
        p = new Poppet(x);
    }

    public static void main(String[] args) {
        new BlankFinal();
        new BlankFinal(47);
    }
}
