package test;

import com.saray.project.chapter2.Task1;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Solution1 {

    static String toCamelCase(String s) {
        StringBuilder sb = new StringBuilder();
        String[] array = s.split("[-_]");
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                String s1 = array[i].substring(0,1).toUpperCase();
                String s2 = array[i].substring(1);
                array[i] = s1 + s2;
            }
            sb.append(array[i]);
        }
        return sb.toString();
    }



    public static void main(String[] args) {
        System.out.println(toCamelCase("I_LOVE_YOU"));
        System.out.println(toCamelCase("the-stealth-warrior"));
        System.out.println(toCamelCase("You_have_chosen_to_translate_this_kata_For_your_convenience" +
                "_we_have_provided_the_existing_test_cases_used_for_the_language_that_you_have_already" +
                "_completed_as_well_as_all_of_the_other_related_fields"));

        Collection s = new HashSet();

        print(s);
        Task1 task1 = new Task1();
        System.out.println(task1.hashCode());
    }

    public static void print(Collection collection) {
        System.out.println("Collection");
    }

    public static void print(Set s) {
        System.out.println("Set");
    }

    public static void print(HashSet hashSet) {
        System.out.println("HashSet");
    }
}

class Solution2 {
    public static void main(String []args) throws InterruptedException {
        Object lock = new Object(); // task будет ждать, пока его не оповестят через lock

        Runnable task = () -> {
            synchronized(lock) {
                try {
                    System.out.println("taking lock");
                    lock.wait();
                    System.out.println("after taking lock");
                } catch (InterruptedException e) {
                    System.out.println("interrupted");}
            }
            // После оповещения нас мы будем ждать, пока сможем взять лок
            System.out.println("thread");};

        Thread taskThread = new Thread(task);
        taskThread.start();

        // Ждём и после этого забираем себе лок, оповещаем и отдаём лок
        Thread.sleep(3000);
        System.out.println("main");
        synchronized(lock) {
            lock.notify();
        }}
}