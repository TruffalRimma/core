package com.saray.project.collections;

import java.util.Stack;

public class StackTest {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        // PUSH _ add elements on the top (top is on the end)
        stack.push("Me");
        stack.push("Not me");
        stack.push("We are");

        System.out.println(stack);

        // POP _ return element on the top and remove it at the same time
        // null - EmptyStackException
        System.out.println(stack.pop());
        System.out.println(stack);

        // EMPTY _ isEmpty
        System.out.println(stack.empty());

        // PEEK - return element on the top (the end of the array)
        System.out.println(stack.peek());
    }
}
