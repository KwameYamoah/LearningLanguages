package com.company;

import java.util.LinkedList;

public class LinkedListCollection {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        System.out.println("First - " + linkedList.getFirst());
        System.out.println("Removing at index 3 -> " + linkedList.remove(3));
        System.out.println("Last - " + linkedList.getLast());
    }
}
