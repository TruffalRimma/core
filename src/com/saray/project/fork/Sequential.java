package com.saray.project.fork;

public class Sequential {

    // без распаралелливания
    public static void main(String[] args) {
        int[] array = Commons.prepareArray();

        long startTime = System.currentTimeMillis();

        double sum = Commons.calculate(array);

        long endTime = System.currentTimeMillis();

        System.out.println(sum);
        System.out.println(endTime - startTime);
    }
}
