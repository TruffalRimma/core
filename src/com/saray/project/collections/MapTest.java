package com.saray.project.collections;

import java.util.*;

public class MapTest {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();

        // PUT - put key and value in map
        // doesnt remember the order!
        map.put(10000, "Me");
        System.out.println(map.put(10000, "MeNew"));
        System.out.println(map.put(20000, "Me2"));
        map.put(2342, "SMBD");
        map.put(null, null);
        System.out.println(map.putIfAbsent(null, "null123"));
        System.out.println(map.putIfAbsent(10000, "Me"));

        System.out.println(map);

        // GET
        System.out.println(map.get(2342));
        System.out.println(map.get(2453678));

        // REMOVE
        System.out.println(map.remove(null));
        System.out.println(map.remove(20000, "Me2"));

        System.out.println(map);

        // CONTAINS
        map.containsKey(null);


        System.out.println(map.keySet());
        System.out.println(map.values());

        for (Map.Entry<Integer, String> entry: map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        TreeMap<Integer, String> map1 = new TreeMap<>(map);
        System.out.println(map1);
        map1.descendingMap();
        System.out.println(map1);

        TreeMap<String, String> stringTreeMap = new TreeMap<>();

        stringTreeMap.put("Elene", "1");
        stringTreeMap.put("elene", "2");
        stringTreeMap.put("eLene", "3");
        stringTreeMap.put("ELene", "4");
        stringTreeMap.put("arina", "5");
//        stringTreeMap.put(null, null);


        System.out.println(stringTreeMap);
    }
}
