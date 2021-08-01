package com.saray.project.chapter8;

/*
Если встроенный объект (КОМПОЗИЦИЯ) используется совместно
с другими объектами - в таких случаях необходимо использовать подсчет ссылок (написанных вручную)
для отслеживания количества объектов, работающих со встроенным объектом
 */

class Shared {
    // 1. сначала инициализируется статик-поле
    private static long counter = 0;

    // 2. инициализация нестатических полей
    private final long id = counter++;
    private int refcount = 0;

    // 3. вызов конструктора
    public Shared() {
        System.out.println("Creating " + this);
    }

    public void addRef() {
        refcount++;
    }

    protected void dispose() {
        // если дело доходит до обращения к методу dispose(),
        // то refcounter сначала уменьшается на один,
        // а после сравнивается с нулём (префиксная форма декремента)
        if (--refcount == 0)
            System.out.println("Disposing " + this);
    }

    public String toString() {
        return "Shared " + id;
    }
}

class Composing {
    private Shared shared;
    private static long counter = 0;

    // при создании нового объекта counter для класса увеличивается на 1
    // после присвоения значения id (постфиксная форма инкремента)
    private final long id = counter++;

    public Composing(Shared shared) {
        System.out.println("Создаем " + this);
        this.shared = shared;
        this.shared.addRef();
    }

    protected void dispose() {
        System.out.println("disposing " + this);
        shared.dispose();
    }

    public String toString() {
        return "Composing " + id;
    }
}

public class ReferenceCounting {
    public static void main(String[] args) {
        Shared shared = new Shared();

        Composing[] composing = {
                new Composing(shared),
                new Composing(shared),
                new Composing(shared),
                new Composing(shared),
                new Composing(shared),
        };

        for (Composing c : composing) {
            c.dispose();
        }
    }
}
