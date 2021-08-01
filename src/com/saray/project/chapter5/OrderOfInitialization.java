package com.saray.project.chapter5;

// запусти debugger, чтобы проследить явно (breakpoint на Window)

/*
В классе House определения объектов Window намеренно разбросаны, чтобы доказать,
что все они инициализируются перед выполнением конструктора или каким-то другим действием.
Вдобавок ссылка w3 заново проходит инициализацию в конструкторе
 */

// При вызове конструктора для создания объекта
// Window выводится сообщение:
class Window {
    Window(int marker) {
        System.out.println("Window (" + marker + ")");
    }
}

class House {
    Window w1 = new Window(1); // Перед конструктором

    House() {
        // Показывает, что выполняется конструктор:
        System.out.println("House()");
        w3 = new Window(33); // Повторная инициализация w3
    }

    Window w2 = new Window(2); // После конструктора

    void f() {
        System.out.println("f()");
    }

    Window w3 = new Window(3); // В конце
}

public class OrderOfInitialization {
    public static void main(String[] args) {
        House h = new House();
        h.f(); // Показывает, что объект сконструирован
    }
}


