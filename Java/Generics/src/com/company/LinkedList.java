package com.company;

public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;

    public LinkedList(){
        head = null;
        tail = null;
    }

    public void addItem(T value){
        Node<T> newNode = new Node<>(value, null);
        if(head == null){
            head = newNode;
        }
        else{
            tail.setNext(newNode);
        }
        tail = newNode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> temp = this.head;

        while(temp.getNext() != null){
            sb.append(temp.getValue()).append(", ");
            temp = temp.getNext();
        }

        sb.append(temp.getValue());
        return sb.toString();
    }
}
