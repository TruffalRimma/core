package com.saray.project.generics;

import java.time.LocalDate;

public class PairTest3 {
    public static void main(String[] args) {

        Manager ceo = new Manager("Gus Greedy", 800000, LocalDate.of(2003, 12, 15));
        Manager cfo = new Manager("Sid Sneaky", 600000, LocalDate.of(2003, 12, 15));

        Pair<Manager> buddies = new Pair<>(ceo, cfo);
        printBuddies(buddies);

        ceo.setSalary(1000000);
        cfo.setSalary(500000);
        Manager[] managers = {ceo, cfo};

        Pair<Employee> result = new Pair<>();
        minmaxSalary(managers, result);

        System.out.println("first: "
                + result.getFirst().getName() + ", second: "
                + result.getSecond().getName());
    }

    // PRODUCER EXTENDS - READ
    public static void printBuddies(
            Pair<? extends Employee> p
    ) {
        Employee first = p.getFirst();
        Employee second = p.getSecond();

        System.out.println(first.getName() + " and " + second.getName()
                + " are buddies.");
    }

    // CONSUMER SUPER - WRITE
    public static void minmaxSalary(
            Manager[] a,
            Pair<? super Manager> result
    ) {
        if (a == null || a.length == 0) return;

        Manager min = a[0];
        Manager max = a[0];

        for (int i = 1; i < a.length; i++) {
            if (min.getSalary() > a[i].getSalary()) min = a[i];
            if (min.getSalary() < a[i].getSalary()) max = a[i];
        }

        result.setFirst(min);
        result.setSecond(max);
    }

    public static void maximumSalary(
            Manager[] a,
            Pair<? super Manager> result
    ) {
        minmaxSalary(a, result);
        PairAlg.swapHelper(result);
    }
}

class PairAlg {

    // USAGE OF <?> - CHECKING FOR NULL
    public static boolean hasNulls(Pair<?> p) {
        return p.getFirst() == null
                || p.getSecond() == null;
    }

    // WILDCARD CAPTURE
    public static void swap(Pair<?> p) {
        swapHelper(p);
    }

    public static <T> void swapHelper(Pair<T> p) {
        T t = p.getFirst();
        p.setFirst(p.getSecond());
        p.setSecond(t);
    }
}