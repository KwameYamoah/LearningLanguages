package com.company;

import java.util.EnumMap;

public class EnumMapCollection {
    public static void main(String[] args) {
        System.out.println("EnumMap is a specialized map implementation that uses only Enum type key");
        System.out.println("It doesn’t allow storing null key");
        System.out.println("EnumMap internally uses the array");
        System.out.println("EnumMap is a specialized map implementation that uses only enum type keys. Due to this, EnumMap is performed better than HashMap.");
        System.out.println("EnumMap stores the keys in the natural order of their keys (order in which the enum constant are declared)");
        System.out.println("Since EnumMap internally uses the array and stores the key in their natural ordering using ordinal(), so there is no probability of collision");
    }
}


