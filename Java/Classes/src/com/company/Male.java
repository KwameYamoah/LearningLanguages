package com.company;

public class Male extends Person {
    public Male(String name, int age) {
        super(name, age);
    }

    @Override
    public void whoAmI(){
        System.out.println("Overridden method");
        System.out.println("I am " + name);
    }

    @Override
    public String toString() {
        return "Male";
    }
}
