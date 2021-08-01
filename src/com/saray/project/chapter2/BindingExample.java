package com.saray.project.chapter2;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BindingExample {
    public static void main(String[] args) {
        Collection c = new HashSet();
        print(c);
    }

    public static void print(Collection collection) {
        System.out.println("Collection");
    }

    public static void print(Set set) {
        System.out.println("Set");
    }

    public static void print(HashSet set) {
        System.out.println("HashSet");
    }
}