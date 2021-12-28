package com.company;

public class WhileStatements {
    public static void main(String[] args) {
        System.out.println("Loops from 0 to 9");
        int i = 0;
        while( i < 10){
            System.out.println(i);
            i++;
        }

        System.out.println("Loops from 0 to 9 but it is printed after been incremented");
        int j = 0;
        while( j < 10){
            j++;
            System.out.println(j);

        }

        System.out.println("Loops from 1 to 10 but interrupted at 6");
        int k = 1;
        while( k <= 10){
            System.out.println(k);
            if(k == 6) break;
            k++;
        }

        System.out.println("Loop from 1 to 10 with do-while");
        int m = 1;
        do{
            System.out.println(m);
            m++;
        }
        while(m <= 10);

        System.out.println("do while runs code before condition so one run is guaranteed");

        int n = 0;
        do{
            System.out.println(n + " (from do while)");
        }while(n != 0);

        while(n != 0){
            System.out.println(n + " (from while)");
            break;
        }

    }
}
