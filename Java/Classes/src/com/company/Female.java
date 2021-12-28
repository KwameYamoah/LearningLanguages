package com.company;

public class Female extends Person{
    public Female(String name, int age) {
        super(name, age);
    }

    public void whoAmI(){
        System.out.println("Overridden method");
        System.out.println("I am " + name);    }

    @Override
    public String toString() {
        return "Female";
    }
}
