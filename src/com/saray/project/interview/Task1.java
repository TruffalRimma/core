package com.saray.project.interview;

interface WithPrimitiveInt {
    void m(int i);
}

interface WithInteger {
    void m(Integer i);
}

public class Task1 implements WithInteger, WithPrimitiveInt {
    @Override
    public void m(int i) {

    }

    @Override
    public void m(Integer i) {

    }
}

