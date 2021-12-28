package com.company;

public class Recursion {
    public static void main(String[] args) {
        System.out.println("Loops from 0 to 10 and prints corresponding fibonacci number");
        System.out.println("fibonacciNumber method recursively repeats while reducing i to get the result");
        for (int i = 0; i < 10; i++) {
            System.out.println(fibonacciNumber(i));
        }
    }

    public static int fibonacciNumber(int i){
        if(i == 0 || i == 1) return i;
        if(i == 2) return 1;
        return fibonacciNumber(i - 1) + fibonacciNumber(i - 2);
    }
}
