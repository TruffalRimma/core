package com.saray.project.chapter3;

public class AutoIncrement {

    /*
    Prefix, или Префиксная форма, описывается перед переменной "++x или --x",
     изначально икрементирует или декрементирует переменную.

    Postfix, или Постфиксная форма, описывается после переменной "x++ или x--",
     икрементирует или декрементирует переменную после вычисления.

    Если вы обратили внимание на слово вычисления, то сделали это не зря,
     так как обе формы работают одинаково, если не используются в вычислениях. */

    public static void noCalc() {
        int x = 10, z = 10;
        x++;
        ++z;

        // В результате обе переменные будут равны 11.
        System.out.println("x: " + x + ", z: " + z);
    }

    public static void withCalc() {
        int x = 10, c = 10;

        final int y = 13 - x++;
        /*
        Начало уравнения.
        y = 13 - x; // Сначала производим вычисление (x все еще 10)
        x += 1; // Производим увеличение на 1
        Конец уравнения.
         */

        final int z = 13 - ++c;
        /*
        Начало уравнения.
        c += 1; // Производим увеличение на 1
        z = 13 - c; // Производим вычисление (c уже 11)
        Конец уравнения.
         */

        // В результате вычислений переменная y будет 3, а вот z будет 2.
        System.out.println("y: " + y + ", z: " + z);
    }

    public static void main(String[] args) {
        noCalc();
        withCalc();
    }
}

class AutoInc {
    public static void main(String[] args) {
        int i = 1;
        System.out.println("i :" + i); // 1
        System.out.println("++i : " + ++i); // Префиксный инкремент - 2
        System.out.println("i++ : " + i++); // Постфиксный инкремент - 2
        System.out.println("i : " + i); // 3
        System.out.println("--i : " + --i); // Префиксный декремент - 2
        System.out.println("i-- : " + i--); // Постфиксный декремен - 2
        System.out.println("i : " + i); // 1
    }
}
