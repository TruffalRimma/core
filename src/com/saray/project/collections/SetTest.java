package com.saray.project.collections;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetTest {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        Set<Integer> set1 = new HashSet<>();
        set1.add(3);
        set1.add(4);
        set1.add(5);
        set1.add(null);

        HashSet<Integer> UNION = new HashSet<>(set1);
        UNION.addAll(set);
        System.out.println(UNION);

        HashSet<Integer> INTERSECT = new HashSet<>(set);
        INTERSECT.retainAll(set1);
        System.out.println(INTERSECT);


//        Iterator<Integer> integerIterator = set.iterator();
//        while (integerIterator.hasNext()) {
//            integerIterator.next();
//            integerIterator.next();
//            integerIterator.remove();
//            integerIterator.remove();
//        }
    }

}

//@FunctionalInterface
//interface My {
//    void my();
//    void notMy();
//}
