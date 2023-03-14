package com.custom.collections;

public class CustomLinkedListIterator<E> implements CustomListIterator {
    private CustomLinkedList linkedList;
    private ListNode currentNode;

    public CustomLinkedListIterator(CustomLinkedList linkedList) {
        this.linkedList = linkedList;
        this.currentNode = this.linkedList.getHead();
    }

    @Override
    public boolean hasNext() {
        if (this.currentNode != null) {
            return true;
        }

        return false;
    }

    @Override
    public E next() {
        ListNode previousNode = this.currentNode;
        this.currentNode = this.currentNode.nextNode;
        return (E) previousNode.data;
    }
}
