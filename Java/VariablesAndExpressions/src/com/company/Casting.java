package com.company;

public class Casting {
    public static void main(String[] args) {
        char char1 = 'a';
        byte byte1 = 127;
        short short1 = 32767;
        int int1 = 100;
        long long1 = 42000000000000L;
        float float1 = 3.703293f;
        double double1 = 3.70329370329370;
        boolean bool1 = true;



        System.out.println("Implicit conversion is when the compiler converts a value to the one it needs on its own");
        System.out.println("Converts to string to output - " + double1);



        System.out.println("Explicit conversion is when we converts a value to the one the compiler needs," +
                " some information can be lost");
        System.out.println("Converts to int as we casted explicitly - " + (int)double1);


    }
}
