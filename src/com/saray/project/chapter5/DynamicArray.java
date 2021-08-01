package com.saray.project.chapter5;

public class DynamicArray {
    public static void main(String[] args) {
        Other.main(new String[]{"fiddle", "de", "dum" });
    }
}
/*
Массив, создаваемый для аргумента other.main(), создается в точке вызова метода,
поэтому вы можете предоставить альтернативные аргументы на момент вызова.
 */
class Other {
    public static void main(String[] args) {
        for (String s : args)
            System.out.print(s + " ");
    }
}
