package com.company;

public class KindOfVariables {
    public static void main(String[] args) {
        byte byte1 = 127;
        byte byte2 = -128;
        System.out.println("A byte is whole numbers between -128 to 127 including 0");
        System.out.println("A byte is stored as 1 byte(8 bits), each bit can have a value of 0 or 1");
        System.out.println("i.e a byte has 2(values)^8(bits) = 256 values");
        System.out.format("%d and %d are examples of  byte\n",byte1, byte2);
        System.out.println();

        short short1 = 32767;
        short short2 = -32768;
        System.out.println("A short is stored as 2 bytes");
        System.out.println("Stores whole numbers from 32767 to -32768");
        System.out.format("%d and %d are examples of short\n", short1, short2);
        System.out.println();

        int int1 = -2147483648;
        int int2 = 2147483647;
        System.out.println("Integers are stored as 4 bytes");
        System.out.println("Stores whole numbers from -2,147,483,648 to 2,147,483,647");
        System.out.format("%d and %d are examples of an integer\n", int1, int2);
        System.out.println();


        long long1 = 9223372036854775807L;
        long long2 = -9223372036854775808L;
        System.out.println("Long are stored as 8 bytes");
        System.out.println("Stores whole numbers from -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807");
        System.out.format("%d and %d are examples of a long number\n", long1, long2);
        System.out.println();


        float float1 = 3.402001f;
        float float2 = -3.4000051f;
        System.out.println("A floating point number is a number that has floating decimals");
        System.out.println("Floats are stored as 4 bytes, Sufficient for storing 6 to 7 decimal digits");
        System.out.format("%f and %f are examples of a Floating point number or float in short\n", float1, float2);
        System.out.println();


        double double1 = 3.468128212139213;
        double double2 = -7.468128212139213;
        System.out.println("Double floating points are similar to float but can store more hence it is more precise");
        System.out.println("Double are stored as 8 bytes, Sufficient for storing up to 15 decimal digits");
        System.out.format("%1.15f and %1.15f are examples of a double floating point number or double in short\n", double1, double2);
        System.out.println();



        boolean bool1 = true;
        boolean bool2 = false;
        System.out.println("A boolean stores a value which is either true or false");
        System.out.println("Boolean are stored as 1 BIT");
        System.out.format("%b and %b are the only possible values for boolean\n", bool1, bool2);
        System.out.println();



        char char1 = 'a';
        char char2 = '1';
        System.out.println("A character stores a single character/letter of ASCII value");
        System.out.println("Character are stored as 2 bytes");
        System.out.format("%c and %c  are examples of characters\n", char1, char2);
        System.out.println();


    }
}
