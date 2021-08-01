package com.saray.project.chapter3;

public class BitwiseOperations {
    public static void main(String[] args) {
        int i = 0b00000100; // 0b + bits
        System.out.println("i = " + i); // 4
        System.out.println("i в двоичной системе счисления = " + Integer.toBinaryString(i) + "\n"); // 100

        System.out.println("---побитовый сдвиг вправо i---");
        System.out.println("---аналогичен делению на 2---");
        System.out.println("в десятичном виде: " + (i >> 1)); // 2
        System.out.println("в двоичном виде: " + binaryStr(i >> 1) + "\n"); // 0b00000010

        System.out.println("---побитовый сдвиг влево i---");
        System.out.println("---аналогичен умножению на 2---");
        System.out.println("в десятичном виде: " + (i << 1)); // 8
        System.out.println("в двоичном виде: " + binaryStr(i << 1) + "\n"); // 0b00001000

        int b1 = 0b00001001;
        int b2 = 0b00001010;

        System.out.println("Побитовое И(AND)");
        printBinary(b1);
        printBinary(b2);
        printBinary(b1 & b2);
        System.out.println();

        /*
        0b00001001
        0b00001010
        0b00001000
         */

        System.out.println("Побитовое ИЛИ(OR)");
        printBinary(b1);
        printBinary(b2);
        printBinary(b1 | b2);
        System.out.println();

        /*
        0b00001001
        0b00001010
        0b00001011
         */

        System.out.println("Исключающее ИЛИ(XOR)");
        printBinary(b1);
        printBinary(b2);
        printBinary(b1 ^ b2);
        System.out.println();

        /*
        0b00001001
        0b00001010
        0b00000011
         */

        System.out.println("Инверсия (NOT)");
        printBinary(b1);
        printBinary(~b1);
        System.out.println();

        /*
        0b00001001
        0b11110110
         */

        util(0b11011000);
    }

    public static void printBinary(int b) {
        System.out.println("0b" + Integer.toBinaryString(0b100000000 | (b & 0xff)).substring(1));
    }

    public static String binaryStr(int b) {
        return "0b" + Integer.toBinaryString(0b100000000 | (b & 0xff)).substring(1);
    }

    public static void util(int b) {
        printBinary(b);

        // установка бита
        printBinary(b | 0b00000010);

        // сброс бита в 0
        printBinary(b | 0b11111101);

        // проверка какой бит стоит в 4 позиции "справа"
        System.out.println((b & 0b00001000) > 0 ? "1" : "0");
    }
}
