package com.company;

public class ForLoopStatements {
    public static void main(String[] args) {
        System.out.println("Loops from 0 to 9 excluding 10");
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }

        System.out.println("Loops from 1 to 10");
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }

        System.out.println("Also Loops from 1 to 10");
        for (int i = 1; i < 11; i++) {
            System.out.println(i);
        }

        System.out.println("Also Loops from 1 to 10 but interrupted at 5");
        for (int i = 1; i < 11; i++) {
            System.out.println(i);
            if(i ==5 ) break;
        }
    }
}
