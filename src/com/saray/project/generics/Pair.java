package com.saray.project.generics;

import java.time.LocalDate;

// <T> - переменная типа
public class Pair <T> {

    private T first;
    private T second;

    public Pair() {
        first = null;
        second = null;
    }

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(T second) {
        this.second = second;
    }
}

/*
у класса может быть больше одной переменной типа
 */

class Pair2<T, U> {

    private T first;
    private U second;

    public Pair2() {
        first = null;
        second = null;
    }

    public Pair2(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(U second) {
        this.second = second;
    }
}

class ArrayAlg {

    public static Pair<String> minmax(String[] a) {
        if(a == null || a.length == 0) return null;

        String min = a[0];
        String max = a[0];

        /*
        сравнение в лексикографическом смысле
         */
        for (int i = 1; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (min.compareTo(a[i]) < 0) max = a[i];
        }

        return new Pair<>(min, max);
    }

    public static <T> T getMiddle(T... a) {
        return a[a.length / 2];
    }
}

class Test {
    public static void main(String[] args) {
        String[] words = {"mary", "had", "a", "little", "lamb", "yas"};
        Pair<String> mm = ArrayAlg.minmax(words);
        System.out.println("min = " + mm.getFirst());
        System.out.println("max = " + mm.getSecond());
        System.out.println(ArrayAlg.getMiddle(words));
        System.out.println(ArrayAlg.<String>getMiddle("John", "Q.", "Public"));
        System.out.println(ArrayAlg.getMiddle("John", "me", 0).getClass());
    }
}

class ArrayAlg2 {

    public static <T extends Comparable> Pair<T> minmax(T[] a) {
        if(a == null || a.length == 0) return null;

        T min = a[0];
        T max = a[0];

        /*
        сравнение в лексикографическом смысле
         */
        for (int i = 1; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (min.compareTo(a[i]) < 0) max = a[i];
        }

        return new Pair<>(min, max);
    }

    public static <T> T getMiddle(T... a) {
        return a[a.length / 2];
    }
}

class Test2 {
    public static void main(String[] args) {
        LocalDate[] birthdays =
                {
                        LocalDate.of(1906, 12, 9),
                        LocalDate.of(1998, 7, 19),
                        LocalDate.of(2001, 6, 7),
                        LocalDate.of(2008, 5, 3)
                };
        Pair<LocalDate> mm = ArrayAlg2.minmax(birthdays);
        System.out.println(mm.getFirst());
        System.out.println(mm.getSecond());
    }
}