package com.custom.collections;

public class CustomLinkedListIterator<E> implements CustomListIterator<E> {
    private CustomLinkedList<E> linkedList;
    private ListNode currentNode;

    public CustomLinkedListIterator(CustomLinkedList<E> linkedList) {
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
