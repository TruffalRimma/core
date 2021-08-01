package com.saray.project.chapter2;

import java.util.Random;

public class ShowProperties {
    public static void main(String[] args) {
        System.getProperties().list(System.out);
        // выводит все свойства системы, в которой запускается программа(информация окружения)

        System.out.println(System.getProperty("user.name"));
        // имя пользователя

        System.out.println(System.getProperty("java.class.path"));
        // установленное значение переменной classpath

        System.out.println(System.getProperty("java.library.path"));
        // путь к библиотекам java

        Random random = new Random(47);
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(10));
        }
    }
}
