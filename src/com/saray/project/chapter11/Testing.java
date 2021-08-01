package com.saray.project.chapter11;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

class Apple {
    // инициализируется нулем, ПРИ СОЗДАНИИ НОВОГО ОБЪЕКТА УВЕЛИЧИВАЕТСЯ НА 1
    private static long counter;
    private final long id = counter++;

    public long id() {
        return id;
    }
}

class Orange {
    public static void main(String[] args) {

    }
}

public class Testing {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        ArrayList apples = new ArrayList();
        for (int i = 0; i < 3; i++)
            apples.add(new Apple());
// Не мешает добавить Orange в apples:
//        apples.add(new Orange());
        for (int i = 0; i < apples.size(); i++)
            System.out.println(((Apple) apples.get(i)).id());
// Объект Orange обнаруживается только во время выполнения
    }
}

