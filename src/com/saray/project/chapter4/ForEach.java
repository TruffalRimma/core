package com.saray.project.chapter4;

/*
for (type itVar : array)
{
    Блок операторов;
}

Где type — тип итерационной переменной (совпадает с типом данных в массиве!),
itVar — её имя, array — массив (тут также может быть другая структура данных,
какая-нибудь коллекция, например, ArrayList), то есть объект, по которому выполняется цикл.
Как вы видите, счётчик в такой конструкции не применяется,
итерационная переменная перебирает элементы массива или коллекции, а не значения индекса.
 */

public class ForEach {

    //метод, который распечатывает все оценки
    public static void printAllGrades(int[] grades) {
        System.out.print("|");

        for (int num : grades) {
            System.out.print(num + "|");
        }
        System.out.println();
    }

    //метод, в котором выводится средняя оценка
    public static double midGrade(int[] numbers) {
        int grade = 0;

        for (int num : numbers) {
            grade = num + grade;
        }
        return ((double) grade / numbers.length);

    }

    //метод в котором вычисляется лучшая (максимальная) оценка
    public static int bestGrade(int[] numbers) {
        int maxGrade = numbers[0];

        for (int num : numbers) {
            if (num > maxGrade) {
                maxGrade = num;
            }
        }
        return maxGrade;
    }

    public static void main(String[] args) {
        //массив оценок
        int[] grades = {5, 10, 7, 8, 9, 9, 10, 12};

        int highest_marks = bestGrade(grades);
        System.out.print("All the grades: ");
        printAllGrades(grades);
        System.out.println("The highest grade is " + highest_marks);
        System.out.println("The average grade is " + midGrade(grades));
    }
}