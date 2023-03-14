package com.custom.collections;

public class ListNode<E> {
    private ListNode<E> previousNode, nextNode;
    private E data;

    public ListNode(E data) {
        this.data = data;
        this.previousNode = null;
        this.nextNode = null;
    }

    public void setNextNode(ListNode<E> nextNode) {
        this.nextNode = nextNode;
    }

    public void setPreviousNode(ListNode<E> previousNode) {
        this.previousNode = previousNode;
    }

    public ListNode<E> getNextNode() {
         return this.nextNode;
    }

    public ListNode<E> getPreviousNode() {
        return this.previousNode;
    }

    public E getData() {
        return this.data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
