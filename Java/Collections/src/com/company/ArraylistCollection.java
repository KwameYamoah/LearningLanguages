package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class ArraylistCollection {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        System.out.println("Adds single value");
        list.add(1);

        System.out.println("Adds multiple values from another collection");
        list.addAll(Arrays.asList(2,3,4,5));

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(6);
        list2.add(9);

        list.addAll(list2);

        System.out.println("Inserts value");
        list.add(6,7);
        System.out.println(list);

        System.out.println("Replaces value");
        list.set(7, 8);
        System.out.println(list);

        System.out.println("Removes value");
        list.remove(7);
        System.out.println(list);

        System.out.println("Removes if predicate is met");
        list.removeIf((Integer e)-> e > 5);
        System.out.println(list);
    }
}
