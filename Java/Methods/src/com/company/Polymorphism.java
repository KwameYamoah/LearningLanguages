package com.company;

public class Polymorphism {
    public static void main(String[] args) {
        System.out.println("Methods must be static to be used in static contexts");
        System.out.println("Static methods can be used in contexts of instance methods though");

        System.out.println("Statically binds to methods based on types inputted");
        System.out.println("Uses int version");
        System.out.println(add(3, 3));

        System.out.println("Uses number version");
        System.out.println(add(3.6, 3));

        System.out.println("Uses float version");
        System.out.println(add(3.6f, 3.4f));

        System.out.println("Uses number version");
        System.out.println(add(3.6, 4.4));
    }

    public static int add(int a, int b){
        return a + b;
    }

    public static float add(float a, float b){
        return a + b;
    }

    public static float add(Number a, Number b){
        return a.floatValue() + b.floatValue();
    }

}
