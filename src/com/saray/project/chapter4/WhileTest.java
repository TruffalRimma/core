package com.saray.project.chapter4;

public class WhileTest {
    static boolean condition() {
        boolean result = Math.random() < 0.99;
        System.out.print(result + ", ");
        return result;
    }

    public static void main(String[] args) {
        while (condition()) {
            System.out.println("Inside 'while'");
        }
        System.out.println("Exited 'while'");
    }
}

/*
Отличие данного оператора от while только в том,
что он является оператором постусловия (сначала выполнит, потом проверит).
То есть, даже если условие не выполняется никогда, всё равно действие будет выполнено один раз.
 */
class DoWhileLoop {
    /*
    do {
        Тело цикла;
    } while (условие выполения);
     */
    public static void main (String[] args) {
        int i = 0;
        do {
            System.out.print(i);
            i++;
        } while (i < 10);
        System.out.println(); //Это можно использовать для простого перевода строки
        do {
            System.out.print("Do...while is cool;");
        } while (2 == 3); //Можно было написать и просто false
    }
}

class ForLoop {
    /*
    for (инициализация; логическое выражение; шаг)
        команда
     */
    public static void main (String[] args ) {
        for (int i = 0; i < 10; i++) {
            System.out.print("Ku-Ku ");
        }
    }
}

class EndlessLoop {
    public static void main(String[] args) {
        // or for(;;)
        while (true) {
            System.out.println("i do");
        }
    }
    // to stop press ctrl + f2
}

class CommaOperator {
    public static void main(String[] args) {
        /*
        Инициализационная часть может содержать любое количество определений переменных одного типа.
        Определение переменных в управляющих выражениях возможно только в цикле for.
        На другие команды выбора или циклов этот подход не распространяется.
         */
        for(int i = 1, j = i + 10; (i < 5) & (j > 0) ; i++, j = i * 2) {
            System.out.println("i = " + i + " j = " + j);
        }
    }
}