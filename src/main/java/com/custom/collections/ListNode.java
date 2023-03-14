package com.custom.collections;

public class ListNode<E> {
    protected ListNode<E> previousNode, nextNode;
    protected E data;

    public ListNode(E data) {
        this.data = data;
        this.previousNode = null;
        this.nextNode = null;
    }
}
