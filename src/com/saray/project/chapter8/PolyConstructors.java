package com.saray.project.chapter8;

/*
ПРИ ВЫЗОВЕ ДИНАМИЧЕСКИ СВЯЗЫВАЕМОГО МЕТОДА В КОНСТРУКТОРЕ ИСПОЛЬЗУЕТСЯ
ПЕРЕОПРЕДЕЛЁННОЕ ОПИСАНИЕ ЭТОГО МЕТОДА
 */
class Glyph {
    void draw() {
        System.out.println("Glyph.draw()");
    }

    Glyph() {
        System.out.println("Glyph() перед вызовом draw()");
        // вызывается переопределенное описание этого метода - до инициализации производного класса
        // соответственно радиус равен нулю так как он не успел инициализироваться переданным значением
        // получается что производный класс вызывает конструктор базового класса
        draw();
        System.out.println("Glyph() после вызова draw()");
    }
}

class RoundGlyph extends Glyph {
    //    private static int radius = 1; // если добавить статик, то сначала инициализируется радиус,
//    а потом в конструкторе у родителя вызовется переопределенный метод с данным значением
    private int radius = 1;

    RoundGlyph(int r) {
        radius = r;
        System.out.println("RoundGlyph(), radius = " + radius);
    }

    @Override
    void draw() {
        System.out.println("RoundGlyph.draw(), radius = " + radius);
    }
}

public class PolyConstructors {
    public static void main(String[] args) {
        new RoundGlyph(5);
        new Glyph();
    }
}
