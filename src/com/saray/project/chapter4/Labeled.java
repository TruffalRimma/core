package com.saray.project.chapter4;

public class Labeled {
    public static void main(String[] args) {
        // найти в многомерном массиве число 4 с помощью меток
        int[][] massiv = new int[][]{{1, 2, 3}, {3, 6, 4}, {4, 9, 0}};

        boolean isNotFound = true; // - способ через boolean

        OuterLoop:
        for (int i = 0; i < massiv.length && isNotFound; i++) {
            InnerLoop:
            // между меткой и циклом никаких доп.команд быть не должно!
            for (int j = 0; j < massiv[0].length; j++) {
                if (massiv[i][j] == 4) {
                    // отсчет с нулевого индекса
                    System.out.println("столбец = " + i + " строчка = " + j);
                    // если использовать break то выход осуществляется только из вложенного цикла
                    isNotFound = false;
                    break OuterLoop;
                    // выход из внешнего цикла
                }
            }
        }
    }
}

class LabeledFor {
    public static void main(String[] args) {
        int i = 0;
        outer:
        // Другие команды недопустимы
        for (; true; ) { // infinite loop
            inner:
            // Другие команды недопустимы
            for (; i < 10; i++) {
                System.out.println("i = " + i);
                if (i == 2) {
                    System.out.println("continue");
                    continue;
                }
                if (i == 3) {
                    System.out.println("break");
                    i++; // В противном случае значение i не увеличивается и будет бесконечный вывод i = 3
                    break;
                }
                if (i == 7) {
                    System.out.println("continue outer");
                    i++; // В противном случае значение i не увеличивается и будет бесконечный вывод i = 7
                    continue outer;
                }
                if (i == 8) {
                    System.out.println("break outer");
//                    break outer; // конец всех итераций
                    return; // можно и так, тот же выход из метода
                }
                // с каждой новой итерацией inner k снова становится 0 и цикл обходится 3 раза,
                // если предыдущие условия оказались false
                for (int k = 0; k < 5; k++) {
                    if (k == 3) {
                        System.out.println("continue inner");
                        continue inner;
                    }
                }
            }
        }
    }
}