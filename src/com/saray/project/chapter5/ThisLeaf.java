package com.saray.project.chapter5;

public class ThisLeaf {
    int age;
    int weight;
    int height;

    ThisLeaf(int age, int weight){
        this.age = age;
        this.weight = weight;
    }

    ThisLeaf(int age, int weight, int height){
        // вы вызываете конструктор с двумя параметрами
        // при вызове одного конструктора через this вызывать второй запрещается
        // вызов другого конструктора должен быть первой выполняемой операцией,
        // иначе компилятор выдаст сообщение об ошибке
        this(age, weight);
        // и добавляете недостающую переменную
        this.height = height;
    }
}

// применяется для возврата ссылки на текущий объект в команде return
class Leaf {
    int i = 0;

    Leaf increment() {
        i++;
        return this;
    }

    void print() {
        System.out.println("i = " + i);
    }

    /*
    Так как метод increment() возвращает ссылку на текущий объект посредством ключевого
    слова this, над одним и тем же объектом легко можно провести множество операций.
     */
    public static void main(String[] args) {
        Leaf x = new Leaf();
        x.increment().increment().increment().print();
    }
}

// Ключевое слово this также может пригодиться для передачи текущего объекта другому/внешнему методу
class Person {
    public void eat(Apple apple) {
        Apple peeled = apple.getPeeled();
        System.out.println("Yummy");
    }
}

class Peeler {
    static Apple peel(Apple apple) {
// ... Снимаем кожуру
        return apple; // Очищенное яблоко
    }
}

class Apple {
    Apple getPeeled() {
        return Peeler.peel(this);
    }
}

class PassingThis {
    public static void main(String[] args) {
        new Person().eat(new Apple());
    }
}

