package com.saray.project.chapter5;

/*
Статические инициализаторы класса Cups выполняются либо при обращении к статическому объекту cups1 в строке с пометкой (1),
либо если строка (1) закомментирована — в строках (2) после снятия комментариев.
Если же и строка(1), и строки (2) закомментированы, static-инициализация класса Cups никогда не выполнится.
Также неважно, будет ли исполнена одна или обе строки (2) программы —
static -инициализация все равно выполняется только один раз.
 */

class Cup {
    Cup(int marker) {
        System.out.println("Cup(" + marker + ")");
    }

    void f(int marker) {
        System.out.println("f(" + marker + ")");
    }
}

class Cups {
    static Cup cup1;
    static Cup cup2;

    static {
        cup1 = new Cup(1);
        cup2 = new Cup(2);
    }

    Cups() {
        System.out.println("Cups()");
    }
}

public class ExplicitStatic {
    public static void main(String[] args) {
        System.out.println("Inside main()");
        Cups.cup1.f(99); // (1)
    }
// static Cups cups1 = new Cups(); // (2)
// static Cups cups2 = new Cups(); // (2)
}
