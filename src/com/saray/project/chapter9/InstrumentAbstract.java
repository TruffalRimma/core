package com.saray.project.chapter9;

// Абстрактные классы и методы

enum Note {
    MIDDLE_C, C_SHARP, B_FLAT
}

abstract class Instrument {
    private int i; // Память выделяется для каждого объекта

    public abstract void play(Note n);

    public String what() {
        return "Instrument";
    }

    public abstract void adjust();
}

class Wind extends Instrument {
    @Override
    public void play(Note n) {
        System.out.println("Wind.play() " + n);
    }

    public String what() {
        return "Wind";
    }

    @Override
    public void adjust() {
    }
}

class Percussion extends Instrument {
    @Override
    public void play(Note n) {
        System.out.println("Percussion.play() " + n);
    }

    public String what() {
        return "Percussion";
    }

    @Override
    public void adjust() {
    }
}

class Stringed extends Instrument {
    @Override
    public void play(Note n) {
        System.out.println("Stringed.play() " + n);
    }

    public String what() {
        return "Stringed";
    }

    @Override
    public void adjust() {
    }
}

class Brass extends Wind {
    @Override
    public void play(Note n) {
        System.out.println("Brass.play() " + n);
    }

    @Override
    public void adjust() {
    }
}

class Woodwind extends Wind {
    // без метода what вернет Wind - КОВАРИАНТНОСТЬ ВОЗВРАЩАЕМЫХ ТИПОВ

    @Override
    public void play(Note n) {
        System.out.println("Woodwind.play() " + n);
    }

    public String what() {
        return "Woodwind";
    }
}

public class InstrumentAbstract {
    // Работа метода не зависит от фактического типа объекта,
    // поэтому типы, добавленные в систему, будут работать правильно
    static void tune(Instrument instrument) {
        System.out.println(instrument.what());
        instrument.adjust();
        instrument.play(Note.MIDDLE_C);
        System.out.println();
    }

    static void tuneAll (Instrument[] instruments) {
        for (Instrument i: instruments) {
            tune(i);
        }
    }

    public static void main(String[] args) {
        Instrument[] orchestra = {
                new Wind(),
                new Percussion(),
                new Stringed(),
                new Brass(),
                new Woodwind()
        };
        tuneAll(orchestra);
    }
}
