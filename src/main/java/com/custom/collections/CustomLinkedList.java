package com.custom.collections;

import java.util.List;

public class CustomLinkedList<E> implements CustomListInterface<E> {
    private ListNode head;
    private int size;

    public CustomLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public boolean isIndexOutOfBounds(int index) {
        if (index < 0  || index >= this.size) {
            return true;
        } else {
            return false;
        }
    }

    public ListNode getHead() {
        return this.head;
    }

    @Override
    public boolean add(E element) {
        ListNode newNode = new ListNode(element);

        if (this.head == null) {
            this.head = newNode;
        } else {
            ListNode currentNode = this.head;

            // We can avoid looping this if we keep track of the tail
            while (currentNode.nextNode != null) {
                currentNode = currentNode.nextNode;
            }
            currentNode.nextNode = newNode;
            newNode.previousNode = currentNode;
        }

        this.size++;
        return true;
    }

    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException {
        if (index == this.size) {
            add(element);
        }
        if (isIndexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException("Index Out Of Bounds!!");
        }

        ListNode newNode = new ListNode(element);
        ListNode currentNode = this.head;

        if (index == 0) {
            this.head.previousNode = newNode;
            newNode.nextNode = this.head;
            this.head = newNode;
        } else {
            int i = 1;
            while (currentNode.nextNode != null && i <= index) {
                currentNode = currentNode.nextNode;
                i++;
            }
            ListNode previousNode = currentNode.previousNode;
            previousNode.nextNode = newNode;
            newNode.previousNode = previousNode;
            newNode.nextNode = currentNode;
            currentNode.previousNode = newNode;
        }
        this.size++;
    }

    @Override
    public boolean contains(Object o) {
        boolean elementIsFound = false;
        ListNode currentNode = this.head;

        while (currentNode.nextNode != null && !elementIsFound) {
            if (currentNode.data.equals(o)) {
                elementIsFound = true;
            } else {
                currentNode = currentNode.nextNode;
            }
        }

        return elementIsFound;
    }

    @Override
    public Object get(int index) throws IndexOutOfBoundsException, IllegalStateException {
        if (this.isEmpty()) {
            throw new IllegalStateException("The List is Empty!");
        }
        if (index == 0) {
            return this.head.data;
        }
        else if (isIndexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException("Index Out Of Bounds! =(");
        }

        ListNode currentNode = this.head;
        for (int i = 1; i <= index; i++) {
            currentNode = currentNode.nextNode;
        }
        return currentNode.data;
    }

    @Override
    public int indexOf(Object o) throws IllegalStateException {
        if (this.isEmpty()) {
            throw new IllegalStateException("The List is Empty!");
        }

        int indexOfElement = 0;
        ListNode currentNode = this.head;

        while(currentNode.nextNode != null) {
            if (currentNode.data.equals(o)) {
                break;
            }
            currentNode = currentNode.nextNode;
            indexOfElement++;
        }

        return indexOfElement;
    }

    @Override
    public boolean isEmpty() {
        if (this.head == null) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public E set(int index, E element) throws IndexOutOfBoundsException {
        if (this.isEmpty()) {
            throw new IllegalStateException("Array List is Empty!");
        }
        if (isIndexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException("Index Out of Bounds!");
        }

        ListNode currentNode = this.head;
        E replacedElement = null;

        for (int i = 0; i <= index; i++) {
            if (i == index) {
                replacedElement = (E) currentNode.data;
                currentNode.data = element;
            }
            currentNode = currentNode.nextNode;
        }

        return replacedElement;
    }

    @Override
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    public void removingAtHead(ListNode currentNode) {
        ListNode nextNode = currentNode.nextNode;
        nextNode.previousNode = null;
        this.head = nextNode;
    }

    public void removingAtMiddle(ListNode currentNode) {
        ListNode previousNode = currentNode.previousNode;
        ListNode nextNode = currentNode.nextNode;
        previousNode.nextNode = nextNode;
        nextNode.previousNode = previousNode;
    }

    public void removingAtEnd(ListNode currentNode) {
        ListNode previousNode = currentNode.previousNode;
        previousNode.nextNode = null;
    }

    @Override
    public Object remove(int index) {
        if (isIndexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException("Index Out of Bounds!");
        }

        ListNode currentNode = this.head;
        ListNode removedNode = null;

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
        this.size--;

        return removedNode.data;
    }

    @Override
    public boolean remove(Object o) {
        boolean elementWasRemoved = false;
        ListNode currentNode = this.head;

        while (currentNode.nextNode != null) {
            if (currentNode.data.equals(o)) {

                if (currentNode.previousNode == null) {
                    removingAtHead(currentNode);
                }
                else if (currentNode.nextNode != null) {
                    removingAtMiddle(currentNode);
                } else {
                    removingAtEnd(currentNode);
                }

                elementWasRemoved = true;
                this.size--;
                break;
            }
            currentNode = currentNode.nextNode;
        }

        return elementWasRemoved;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    public CustomListIterator<E> iterator() {
        return new CustomLinkedListIterator<E>(this);
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "The List is Empty!";
        }
        String listToPrint = "[";
        ListNode currentNode = this.head;

        while (currentNode.nextNode != null) {
            listToPrint += currentNode.data.toString() + ", ";
            currentNode = currentNode.nextNode;
        }

        listToPrint += currentNode.data.toString() + ", ";
        listToPrint = listToPrint.substring(0, listToPrint.length() - 2) + "]";
        return listToPrint;
    }

}
