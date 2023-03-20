package com.custom.collections.linkedlist;

import com.custom.collections.CustomIterator;

class CustomLinkedListIterator<E> implements CustomIterator<E> {
    private ListNode<E> currentNode;

    public CustomLinkedListIterator(ListNode<E> head) {
        currentNode = head;
    }

    @Override
    public boolean hasNext() {
        return currentNode != null;
    }

    @Override
    public E next() {
        ListNode<E> previousNode = currentNode;
        currentNode = currentNode.nextNode;

        return previousNode.data;
    }
}
