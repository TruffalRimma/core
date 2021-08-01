package com.saray.project.generics;

public class InstanceCreature<T> {
    // bounded wildcard type
    Class<? extends T> clazz;

    InstanceCreature(Class<? extends T> clazz) {
        this.clazz = clazz;
    }

    T newInstance() throws ReflectiveOperationException {
        // если нашелся открытый конструктор без параметров!
        return clazz.newInstance();
    }
}

class Testing {
    public static void main(String[] args) {
        InstanceCreature<String> instanceCreature = new InstanceCreature<>(String.class);
    }
}