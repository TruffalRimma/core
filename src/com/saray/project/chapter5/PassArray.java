package com.saray.project.chapter5;

// Использование массива для передачи методу переменного
// количества аргументов. Это старый стиль подхода
// к обработке списка аргументов переменной длины,
class PassArray {
    // требует, чтобы аргументы были вручную помещены в массив до вызова метода vaTest().
    static void vaTest(int v[]) {
        System.out.print("Количество аргументов: " + v.length +
                " Содержимое: ");
        for (int x : v)
            System.out.print(x + " ");
        System.out.println();
    }

    public static void main(String args[]) {
        // Обратите внимание на способ создания массива
        // для хранения аргументов.
        int n1[] = {10};
        int n2[] = {1, 2, 3};
        int n3[] = {};
        vaTest(n1); // 1 аргумент
        vaTest(n2); // 3 аргумента
        vaTest(n3); // без аргументов
    }
}

// Демонстрация использования списка аргументов переменной длины,
class VarArgs {
    // теперь vaTest() использует список аргументов переменной длины,
    static void vaTest(int ... v) {
        System.out.print("Количество аргументов: " + v.length +
                " Содержимое: ");
        for(int x : v)
            System.out.print(x + " ");
        System.out.println();
    }
    /*
    Аргументы автоматически помещаются в массив и передаются переменной v.
    В случае отсутствия аргументов длина массива равна нулю.
     */
    public static void main(String[] args){
        // Обратите внимание на возможные способы вызова
        // vaTest() с переменным количеством аргументов.
        vaTest(10); // 1 аргумент
        vaTest(1, 2, 3); // 3 аргумента
        vaTest(); // без аргументов
    }
}

class NewVarArgs {
    static void printArray(Object... args) {
        for (Object obj : args)
            System.out.print(obj + " ");
        System.out.println();
    }

    public static void main(String[] args) {
// Можно передать отдельные элементы:
        printArray(new Integer(47), new Float(3.14),
                new Double(11.11));
        printArray(47, 3.14F, 11.11);
        printArray("paз", "два", "три");
        printArray(new PassArray(), new PassArray(), new PassArray());
// Или массив:
        printArray((Object[]) new Integer[]{1, 2, 3, 4});
        printArray(); // Пустой список тоже возможен
    }
}