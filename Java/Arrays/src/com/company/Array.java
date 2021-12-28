package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Array {
    public static void main(String[] args) {

        int[] array = new int[10];
        System.out.println("Array can be instantiated with a given size");
        System.out.println("Array size can't be changed after initialising");

        int[] array1 = {1,2,3,4,5};
        int[] array2 = new int[]{1,2,3,4,5};
        System.out.println("Type can be omitted for instantiate");


        array[0] = 6;
        System.out.println("Array values can be changed by index - " + Arrays.toString(array));


        System.out.println("Arraylist size can be resized and uses an array in the implementation");
        System.out.println("When size needs to be change, current values are copied into a bigger array");
        ArrayList<Integer> arrayList = new ArrayList<>(10);
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.set(0, 6);
        System.out.println(arrayList);
    }
}
