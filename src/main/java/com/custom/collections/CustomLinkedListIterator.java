package com.custom.collections;

public class CustomLinkedListIterator<E> implements CustomListIterator<E> {
    private ListNode<E> currentNode;

    public CustomLinkedListIterator(ListNode<E> head) {
        this.currentNode = head;
    }

    @Override
    public boolean hasNext() {
        return this.currentNode != null;
    }

    @Override
    public E next() {
        ListNode<E> previousNode = this.currentNode;
        this.currentNode = this.currentNode.getNextNode();
        return previousNode.getData();
    }
}
