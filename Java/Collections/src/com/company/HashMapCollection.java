package com.company;

import java.util.HashMap;

public class HashMapCollection {
    public static void main(String[] args) {
        System.out.println("HashMap is non-synchronized. It is not thread-safe and can’t be shared between many threads without proper synchronization code");
        System.out.println("HashMap allows one null key and multiple null values");
        System.out.println("HashMap is generally preferred over HashTable if thread synchronization is not needed");
        HashMap<Integer, String> numbers = new HashMap<>();
        numbers.put(1, "one");
        numbers.put(2, "two");
        numbers.put(3, "three");
        System.out.println(numbers);

        numbers.remove(2);
        System.out.println(numbers);

    }
}
