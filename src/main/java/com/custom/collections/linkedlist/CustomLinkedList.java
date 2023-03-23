package com.custom.collections.linkedlist;

import com.custom.collections.CustomListInterface;
import com.custom.collections.CustomIterator;

public class CustomLinkedList<E> implements CustomListInterface<E> {
    private ListNode<E> head, tails;
    private int size;

    public CustomLinkedList() {
    }

    public boolean isIndexOutOfBounds(int index) {
        return index < 0 || index >= size;
    }

    @Override
    public boolean add(E element) {
        ListNode<E> newNode = new ListNode<>(element);

        if (head == null) {
            head = newNode;
        } else {
            ListNode<E> currentNode = tails;

            currentNode.nextNode = newNode;
            newNode.previousNode = currentNode;
        }
        tails = newNode;

        size++;
        return true;
    }

    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException {
        if (index == size) {
            add(element);
            return;
        }

        if (isIndexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException("Index Out Of Bounds!!");
        }

        ListNode<E> newNode = new ListNode<>(element);
        ListNode<E> currentNode = head;

        if (index == 0) {
            head.previousNode = newNode;
            newNode.nextNode = head;
            head = newNode;
        } else {
            int i = 1;
            while (currentNode.nextNode != null && i <= index) {
                currentNode = currentNode.nextNode;
                i++;
            }
            ListNode<E> previousNode = currentNode.previousNode;
            previousNode.nextNode = newNode;
            newNode.previousNode = previousNode;
            newNode.nextNode = currentNode;
            currentNode.previousNode = newNode;
        }
        size++;
    }

    @Override
    public boolean contains(E element) {
        boolean elementIsFound = false;
        ListNode<E> currentNode = head;

        while (currentNode != null && !elementIsFound) {
            if (currentNode.data.equals(element)) {
                elementIsFound = true;
            } else {
                currentNode = currentNode.nextNode;
            }
        }

        return elementIsFound;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException, IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("The List is Empty!");
        }
        if (index == 0) {
            return head.data;
        }
        else if (isIndexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException("Index Out Of Bounds! =(");
        }

        ListNode<E> currentNode = head;
        for (int i = 1; i <= index; i++) {
            currentNode = currentNode.nextNode;
        }
        return currentNode.data;
    }

    @Override
    public int indexOf(E e) throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("The List is Empty!");
        }

        int indexOfElement = -1;
        int index = 0;
        ListNode<E> currentNode = head;

        while(currentNode != null) {
            if (currentNode.data.equals(e)) {
                indexOfElement = index;
                break;
            }
            currentNode = currentNode.nextNode;
            index++;
        }

        return indexOfElement;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E set(int index, E element) throws IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new IllegalStateException("Array List is Empty!");
        }
        if (isIndexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException("Index Out of Bounds!");
        }

        ListNode<E> currentNode = head;
        E replacedElement = null;

        for (int i = 0; i <= index; i++) {
            if (i == index) {
                replacedElement = currentNode.data;
                currentNode.data = element;
            }
            currentNode = currentNode.nextNode;
        }

        return replacedElement;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    private void removingAtHead(ListNode<E> currentNode) {
        if (size == 1) {
            head = null;
            tails = null;
        } else {
            ListNode<E> nextNode = currentNode.nextNode;
            nextNode.previousNode = null;
            head = nextNode;
        }
    }

    private void removingAtMiddle(ListNode<E> currentNode) {
        ListNode<E> previousNode = currentNode.previousNode;
        ListNode<E> nextNode = currentNode.nextNode;
        previousNode.nextNode = nextNode;
        nextNode.previousNode = previousNode;
    }

    private void removingAtEnd(ListNode<E> currentNode) {
        ListNode<E> previousNode = currentNode.previousNode;
        if (previousNode != null) {
            previousNode.nextNode = null;
            tails = previousNode;
        }
    }

    @Override
    public E remove(int index) {
        if (isIndexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException("Index Out of Bounds!");
        }

        ListNode<E> currentNode = head;
        ListNode<E> removedNode = head;

        if (index == 0) {
            removingAtHead(currentNode);
            return removedNode.data;
        }

        for (int i = 1; i <= index; i++) {
            currentNode = currentNode.nextNode;
            if (i == index) {
                if (currentNode.nextNode != null) {
                    removingAtMiddle(currentNode);
                } else {
                    removingAtEnd(currentNode);
                }
                removedNode = currentNode;
                break;
            }
        }
        size--;

        return removedNode.data;
    }

    @Override
    public boolean remove(E e) {
        boolean elementWasRemoved = false;
        ListNode<E> currentNode = head;

        while (currentNode != null) {
            if (currentNode.data.equals(e)) {
                if (currentNode.previousNode == null) {
                    removingAtHead(currentNode);
                }
                if (currentNode.nextNode != null && currentNode.previousNode != null) {
                    removingAtMiddle(currentNode);
                }
                if (currentNode.nextNode == null) {
                    removingAtEnd(currentNode);
                }

                elementWasRemoved = true;
                size--;
                break;
            }
            currentNode = currentNode.nextNode;
        }

        return elementWasRemoved;
    }

    public CustomIterator<E> iterator() {
        return new CustomLinkedListIterator<>(head);
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "[]";
        }
        StringBuilder listToPrint = new StringBuilder("[");
        ListNode<E> currentNode = head;

        while (currentNode.nextNode != null) {
            listToPrint.append(currentNode.data.toString()).append(", ");
            currentNode = currentNode.nextNode;
        }

        listToPrint.append(currentNode.data.toString()).append(", ");
        listToPrint = new StringBuilder(listToPrint.substring(0, listToPrint.length() - 2) + "]");
        return listToPrint.toString();
    }

}
