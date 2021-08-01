package com.saray.project.chapter8;

import java.util.Random;

// Базовый класс - устанавливает общий интерфейс для всех классов-наследников
class Shape {
    public void draw() {
    }

    public void erase() {
    }
}

class Circle extends Shape {
    // переопределение классом-наследником интерфейса базового класса
    // в учебнике без аннотации
    @Override
    public void draw() {
        System.out.println("Circle.draw()");
    }

    @Override
    public void erase() {
        System.out.println("Circle.erase()");
    }
}

class Square extends Shape {
    @Override
    public void draw() {
        System.out.println("Square.draw()");
    }

    @Override
    public void erase() {
        System.out.println("Square.erase()");
    }
}

class Triangle extends Shape {
    @Override
    public void draw() {
        System.out.println("Triangle.draw()");
    }

    @Override
    public void erase() {
        System.out.println("Triangle.erase()");
    }
}

// фабрика наследников
class RandomShapeGenerator {
    private Random random = new Random(47);

    public Shape next() {
        switch (random.nextInt(3)) {
            default:
            case 0:
                // восходящее преобразование
                return new Circle();
            case 1:
                return new Square();
            case 2:
                return new Triangle();
        }
    }
}

public class ShapesGenerator {
    private static RandomShapeGenerator generator = new RandomShapeGenerator();

    public static void main(String[] args) {
        Shape[] shapes = new Shape[9];

        // Заполняем массив фигурами
        for(int i = 0; i < shapes.length; i++) {
            shapes[i] = generator.next();
        }

        // Полиморфные вызовы методов - ПОЗДНЕЕ СВЯЗЫВАНИЕ
        for (Shape shape: shapes) {
            shape.draw();
            shape.erase();
        }
    }
}
