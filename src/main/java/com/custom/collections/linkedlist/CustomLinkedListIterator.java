package com.custom.collections.linkedlist;

import com.custom.collections.CustomListIterator;

class CustomLinkedListIterator<E> implements CustomListIterator<E> {
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
        ListNode<E> previousNode = currentNode;
        currentNode = currentNode.nextNode;

        return previousNode.data;
    }
}