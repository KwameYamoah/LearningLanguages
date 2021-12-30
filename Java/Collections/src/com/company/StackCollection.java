package com.company;

import java.util.Stack;

public class StackCollection {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println("Stack is a last in, first out");
        System.out.println("Pushed 5 numbers on stack");

        System.out.println("Peeking top of item - " + stack.peek());
        System.out.println("Popping remove item and returns it");
        System.out.println("Popping an item - " +stack.pop());

        System.out.println("Peeking top of item - " + stack.peek());
        System.out.println("Popping an item - " +stack.pop());
        System.out.println("Peeking top of item - " + stack.peek());
        System.out.println(stack);


    }
}
