package com.company;

import java.util.PriorityQueue;

public class PriorityQueueCollection {
    public static void main(String[] args) {
        System.out.println("It is an Interface that is a FirstIn – FirstOut Data Structure where the elements are added from the back");
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        System.out.println("Adding 50");
        priorityQueue.add(50);
        System.out.println(priorityQueue);

        System.out.println("Adding 20");
        priorityQueue.add(20);
        System.out.println(priorityQueue);

        System.out.println("Adding 10");
        priorityQueue.add(10);
        System.out.println(priorityQueue);

        System.out.println("Adding 30");
        priorityQueue.add(30);
        System.out.println(priorityQueue);

        System.out.println("Adding 40");
        priorityQueue.add(40);
        System.out.println(priorityQueue);

        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue);

        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue);

        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue);
    }
}
