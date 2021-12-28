package com.company;

public abstract class Person {
    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void whoAmI(){
        System.out.println("Overridden method");
        System.out.println("I am " + name);
    }

    @Override
    public String toString() {
        return "Person";
    }
}
