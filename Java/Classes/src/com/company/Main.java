package com.company;

public class Main {
    public static void main(String[] args) {
        System.out.println("Encapsulation - Person's name and age are encapsulated and can be only by accessed Person and sub-classes");
        System.out.println("Abstraction - Person is an abstraction of Male and Female");
        Male m = new Male("Jack", 26);
        System.out.println(m);
        m.whoAmI();
        System.out.println();

        Female f = new Female("Veronica", 20);
        System.out.println(f);
        f.whoAmI();
        System.out.println();

        System.out.println("Polymorphism - Dynamically bounded during runtime");
        System.out.println("Inheritance - Male and Female is a sub-child of person so they both inherit method and variables from Person");
        Person d = new Male("Bob", 18);
        System.out.println(d);
        d.whoAmI();

    }
}
