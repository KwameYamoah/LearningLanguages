package com.company;

import java.util.ArrayDeque;

public class ArrayDequeCollection {
    public static void main(String[] args) {
        System.out.println("It is a Doubly Ended Queue in which you can insert the elements from both sides. It is an interface that implements the Queue");
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.add(1);
        deque.add(2);
        deque.add(3);
        deque.remove(2);
        System.out.println(deque);
    }
}
