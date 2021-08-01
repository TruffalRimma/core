package com.saray.project.chapter10;

/*
ИЕРАРХИЯ НАСЛЕДОВАНИЯ И ИЕРАРХИЯ ВЛОЖЕННОСТИ
С введением внутренних классов появляются две отдельные иерархии,
к которым может принадлежать любой класс.
Первая – это иерархия классов, от родителя к подклассу, определяющая поля и методы, наследуемые внутренним классом.
Вторая – это иерархия вложенности, от окружающего класса к внутреннему классу, определяющая набор полей и методов,
который входит в область видимости внутреннего класса (и поэтому этот набор ему доступен).
 */

class OuterClass {
    String string = "Outer";

    class InnerClass extends External {
        void printString() {
            System.out.println(string);
            System.out.println(OuterClass.this.string);
        }
    }
}

class External {
    String string = "External";
}

public class NestedVSInherit {
    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
        innerClass.printString();
    }
}
