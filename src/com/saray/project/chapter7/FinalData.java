package com.saray.project.chapter7;

import java.util.Random;

class Value {
    int i; // доступ в пределах пакета

    public Value(int i) {
        this.i = i;
    }
}

public class FinalData {
    private static Random random = new Random(47);
    private String id;

    public FinalData(String id) {
        this.id = id;
    }

    // Могут быть константами времени компиляции
    private final int valueOne = 9;
    private static final int VALUE_TWO = 99;

    // Типичная открытая константа
    public static final int VALUE_THREE = 39;

    // Не может быть константой времени компиляции
    private final int i4 = random.nextInt(20);
    static final int INT_5 = random.nextInt(20);
    private Value v1 = new Value(11);
    private final Value v2 = new Value(22);
    private static final Value VAL_3 = new Value(33);

    // Массивы
    private final int[] array = {1, 2, 3, 4, 5, 6};

    public String toString() {
        return id + ": " + "i4 = " + i4 + ", INT_5 = " + INT_5;
    }

    public static void main(String[] args) {
        FinalData fd1 = new FinalData("fd1");
//        fd1.valueOne++; !Ошибка: значение нельзя изменить
        fd1.v2.i++; // Объект не является неизменным!
        fd1.v1 = new Value(9); // OK - не является неизменным
        for (int i = 0; i < fd1.array.length; i++) {
            fd1.array[i]++; // Объект не является неизменным
        }
//        fd1.v2 = new Value(0);     // Ошибка: ссылку
//        fd1.VAL_3 = new Value(1);  // нельзя изменить
//        fd1.array = new int[3];    // !!!
        System.out.println(fd1);
        System.out.println("Создаем FinalData");
        FinalData fd2 = new FinalData("fd2");
        System.out.println(fd1);
        System.out.println(fd2);
    }
}
