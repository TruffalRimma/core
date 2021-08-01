package com.saray.project.chapter8;

// Нисходящее преобразование и динамическое определение типов RTTI

class Useful {
    public void f() {}
    public void g() {
    }
}

class MoreUseful extends Useful {
    public void f() {}
    public void g() {
    }
    public void u() {}
}

public class RTTI {
    public static void main(String[] args) {
        Useful[] x = {
                new Useful(),
                new MoreUseful()
        };

        x[0].f();
        x[1].g();

        // Метод не найден в классе Useful на стадии компиляции
        // ! x[1].u();

        ((MoreUseful)x[1]).u(); // Нисходящее преобразование // RTTI
//        ((MoreUseful)x[0]).u(); // Исключение java.lang.ClassCastException - Useful cannot be cast to MoreUseful
    }
}
