package com.saray.project.chapter10;

/*
ВНУТРЕННИЙ НЕСТАТИЧЕСКИЙ КЛАСС (inner class)
.THIS - доступ к объекту внешнего класса
.NEW - прямое создание объекта внутреннего нестатического класса
 */

public class Outer {
    void f() {
        System.out.println("Outer.f()");
    }

    public class Inner {
        public Outer outer() {
            return Outer.this;
        }
    }

    // как правило внешний класс содержит метод, возвращающий ссылку на внутренний класс
    public Inner inner() {
        return new Inner();
    }

    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner = outer.inner();
        inner.outer().f();

        // для создания внутреннего класса нужна ссылка на объект внешнего класса
        // так как эти объекты связаны - если это нестатический класс
        Outer.Inner inner1 = outer.new Inner();
    }
}

