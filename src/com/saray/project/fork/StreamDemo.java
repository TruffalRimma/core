package com.saray.project.fork;

import java.util.Arrays;

public class StreamDemo {

    // по скорости также как ForkJoin но без кучи кода
    public static void main(String[] args) {
        int[] array = Commons.prepareArray();

        long startTime = System.currentTimeMillis();

        double sum = Arrays.stream(array)
                .parallel()
                .mapToDouble(Commons::function)
                .sum();

        long endTime = System.currentTimeMillis();

        System.out.println(sum);
        System.out.println(endTime - startTime);
    }
}
