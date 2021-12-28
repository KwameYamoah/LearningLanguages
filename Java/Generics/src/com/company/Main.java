package com.company;

public class Main {
    public static void main(String[] args) {
        System.out.println("This custom linked lists uses generics to allow the data structure to use various types");
        LinkedList<Object> list = new LinkedList<>();
        list.addItem("Hello");
        list.addItem(5);
        System.out.println(list);
    }
}
