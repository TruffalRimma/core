package com.saray.project.collections;

/*
ОПЕРАЦИИ СО СВЯЗНЫМИ СПИСКАМИ
 */

import java.util.*;

public class LinkedListTest {
    public static void main(String[] args) {
        List<String> a = new LinkedList<>();
        ArrayList<String> a1 = new ArrayList<>();
        Collections.sort(a);

        // добавление в конец списка
        a1.add("Amy"); // boolean
        a1.add("Carl");
        a1.add("Erica");
        a1.add("Carl");
        a1.add(2, "Rimma"); // void

        System.out.println(a1);

        a1.remove("Carl");

        System.out.println(a1);

        List<String> b = new LinkedList<>();

        b.add("Bob");
        b.add("Doug");
        b.add("Frances");
        b.add("Gloria");

        // объединяем слова из 2х списков
        ListIterator<String> aIter = a.listIterator();
        Iterator<String> bIter = b.iterator();

        while (bIter.hasNext()) {
            if (aIter.hasNext()) aIter.next();
            aIter.add(bIter.next());
        }

        // toString() - список выведется в виде массива
        System.out.println(a);

        // удалить каждое второе слово из связного списка b
        bIter = b.iterator();

        while (bIter.hasNext()) {
            bIter.next(); // пропускаем один элемент
            if (bIter.hasNext()) {
                bIter.next();
                bIter.remove();
            }
        }

        System.out.println(b);

        /*
        удаляем из а все, что есть в b
         */
//        a.removeAll(b);

        // удалить все, чего нет в b
        a.retainAll(b);

        System.out.println(a);

        ListIterator<String> aIter1 = a.listIterator(2);

        while (aIter1.hasPrevious()) {
            System.out.println(aIter1.previous());
        }

    }
}
