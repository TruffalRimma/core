package com.saray.project.chapter5;


class Bowl {
    Bowl(int marker) {
        System.out.println("Bowl(" + marker + ")");
    }

    void f1(int marker) {
        System.out.println("f1(" + marker + ")");
    }
}

class Table {
    // 1.1 шаг
    // инициализируется только один раз
    static Bowl bowl1 = new Bowl(1);

    // 1.3 шаг
    Table() {
        System.out.println("Table()");
        bowl2.f1(1);
    }

    void f2(int marker) {
        System.out.println("f2(" + marker + ")");
    }

    // 1.2 шаг
    static Bowl bowl2 = new Bowl(2);
}

class Cupboard {
    // 2.3 шаг
    // повторно инициализируется при создании нового объекта
    Bowl bowl3 = new Bowl(3);

    // 2.1 шаг
    static Bowl bowl4 = new Bowl(4);

    // 2.4 шаг
    Cupboard() {
        System.out.println("Cupboard()");
        bowl4.f1(2);
    }

    void f3(int marker) {
        System.out.println("f3(" + marker + ")");
    }

    // 2.2 шаг
    static Bowl bowl5 = new Bowl(5);
}

public class StaticInitialization {
    public static void main(String[] args) {
        // 3й шаг
        System.out.println("Создание нового объекта Cupboard в main()");
        new Cupboard();
        System.out.println("Создание нового объекта Cupboard в main()");
        new Cupboard();
        table.f2(1);
        cupboard.f3(1);
    }

    static Table table = new Table(); // 1й шаг
    static Cupboard cupboard = new Cupboard(); // 2й шаг
}

