package com.saray.project.chapter9;

// ИНТЕРФЕЙС

interface Instrument2 {
    // Константа времени компиляции - является и static и final
    int VALUE = 5;

    // Определения методов недопустимы
    // public abstract
    void play(Note n);
    void adjust();
}

class Wind2 implements Instrument2 {
    @Override
    public void play(Note n) {
        System.out.println(this + ".play() " + n);
    }

    @Override
    public void adjust() {
        System.out.println(this + ".adjust()");
    }

    @Override
    public String toString() {
        return "Wind";
    }
}

class Percussion2 implements Instrument2 {
    @Override
    public void play(Note n) {
        System.out.println(this + ".play() " + n);
    }

    @Override
    public void adjust() {
        System.out.println(this + ".adjust()");
    }

    @Override
    public String toString() {
        return "Percussion";
    }
}

class Stringed2 implements Instrument2 {
    @Override
    public void play(Note n) {
        System.out.println(this + ".play() " + n);
    }

    @Override
    public void adjust() {
        System.out.println(this + ".adjust()");
    }

    @Override
    public String toString() {
        return "Stringed";
    }
}

class Brass2 extends Wind2 {
    @Override
    public String toString() {
        return "Brass";
    }
}

class Woodwind2 extends Wind2 {
    @Override
    public String toString() {
        return "Woodwind";
    }
}

public class InstrumentInterface {
    // Работа метода не зависит от фактического типа объекта,
    // поэтому типы, добавленные в систему, будут работать правильно
    static void tune(Instrument2 instrument) {
        instrument.adjust();
        instrument.play(Note.MIDDLE_C);
    }

    static void tuneAll (Instrument2[] instruments) {
        for (Instrument2 i: instruments) {
            tune(i);
        }
    }

    public static void main(String[] args) {
        Instrument2[] orchestra = {
                new Wind2(),
                new Percussion2(),
                new Stringed2(),
                new Brass2(),
                new Woodwind2()
        };
        tuneAll(orchestra);
    }


}