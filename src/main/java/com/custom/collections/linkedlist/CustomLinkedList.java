package com.custom.collections.linkedlist;

import com.custom.collections.CustomListInterface;
import com.custom.collections.CustomListIterator;

public class CustomLinkedList<E> implements CustomListInterface<E> {
    private ListNode<E> head;
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
            ListNode<E> currentNode = head;

            // We can avoid looping this if we keep track of the tail
            while (currentNode.nextNode != null) {
                currentNode = currentNode.nextNode;
            }

            currentNode.nextNode = newNode;
            newNode.previousNode = currentNode;
        }

        size++;
        return true;
    }

    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException {
        if (index == size) {
            add(element);
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

        while (currentNode.nextNode != null && !elementIsFound) {
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

        int indexOfElement = 0;
        ListNode<E> currentNode = head;

        while(currentNode.nextNode != null) {
            if (currentNode.data.equals(e)) {
                break;
            }
            currentNode = currentNode.nextNode;
            indexOfElement++;
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

    public void removingAtHead(ListNode<E> currentNode) {
        ListNode<E> nextNode = currentNode.nextNode;
        nextNode.previousNode = null;
        head = nextNode;
    }

    public void removingAtMiddle(ListNode<E> currentNode) {
        ListNode<E> previousNode = currentNode.previousNode;
        ListNode<E> nextNode = currentNode.nextNode;
        previousNode.nextNode = nextNode;
        nextNode.previousNode = previousNode;
    }

    public void removingAtEnd(ListNode<E> currentNode) {
        ListNode<E> previousNode = currentNode.previousNode;
        previousNode.nextNode = null;
    }

    @Override
    public E remove(int index) {
        if (isIndexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException("Index Out of Bounds!");
        }

        ListNode<E> currentNode = head;
        ListNode<E> removedNode = null;

        if (index == 0) {
            removedNode = currentNode;
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

        if (removedNode == null) {
            return null;
        }
        return removedNode.data;
    }

    @Override
    public boolean remove(E e) {
        boolean elementWasRemoved = false;
        ListNode<E> currentNode = head;

        while (currentNode.nextNode != null) {
            if (currentNode.data.equals(e)) {
                if (currentNode.previousNode == null) {
                    removingAtHead(currentNode);
                }
                if (currentNode.nextNode == null) {
                    removingAtEnd(currentNode);
                }
                if (currentNode.nextNode != null) {
                    removingAtMiddle(currentNode);
                }

                elementWasRemoved = true;
                size--;
                break;
            }
            currentNode = currentNode.nextNode;
        }

        return elementWasRemoved;
    }

    public CustomListIterator<E> iterator() {
        return new CustomLinkedListIterator<>(head);
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "The List is Empty!";
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
