package com.saray.project.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HashCodeEx {
    public static void main(String[] args) {
        Map<Student, Double> studentDoubleMap = new HashMap<>();
        Student st1 = new Student("111", "11", 3);
        Student st2 = new Student("222", "22", 2);
        Student st3 = new Student("333", "33", 1);

        studentDoubleMap.put(st1, 3.14d);
        studentDoubleMap.put(st2, 4.14d);
        studentDoubleMap.put(st3, 5d);

        System.out.println(studentDoubleMap);

        Student st4 = new Student("333", "33", 1);
        System.out.println(studentDoubleMap.containsKey(st4));
    }
}

class Student {
    String name;
    String surname;
    int course;

    public Student(String name, String surname, int course) {
        this.name = name;
        this.surname = surname;
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", course=" + course +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return course == student.course && Objects.equals(name, student.name) && Objects.equals(surname, student.surname);
    }

    @Override
    public int hashCode() {
        // плохая реализация
        return name.length()*7 + name.length()*11 + course*53;
    }
}
