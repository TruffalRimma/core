package com.saray.project.generics;

import java.time.LocalDate;

/*
интервал дат задается парой объектов типа LocalDate
 */
public class DateInterval extends Pair<LocalDate>{

    LocalDate first;
    LocalDate second;

    public DateInterval(LocalDate of, LocalDate of1) {
        super.setFirst(of);
        super.setSecond(of1);
        first = of;
        second = of1;
    }

    // переопределение метода из суперкласса чтобы значение second не было меньше второго
    public void setSecond(LocalDate second) {
        if (second.compareTo(getFirst()) >= 0) {
            super.setSecond(second);
        }
    }

    public static void main(String[] args) {
        Object obj = new Object();
        Pair<LocalDate> pair = new DateInterval(LocalDate.of(1815, 7, 7), LocalDate.of(1815, 7, 7));
        LocalDate date = LocalDate.of(2001, 12, 1);
        pair.setSecond(date);
        System.out.println(pair.getSecond());
    }
}
