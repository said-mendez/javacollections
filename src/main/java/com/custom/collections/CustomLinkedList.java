package com.custom.collections;

public class CustomLinkedList<E> implements CustomListInterface<E> {
    private ListNode<E> head;
    private int size;

    public CustomLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public boolean isIndexOutOfBounds(int index) {
        return index < 0 || index >= this.size;
    }

    @Override
    public boolean add(E element) {
        ListNode<E> newNode = new ListNode<>(element);

        if (this.head == null) {
            this.head = newNode;
        } else {
            ListNode<E> currentNode = this.head;

            // We can avoid looping this if we keep track of the tail
            while (currentNode.getNextNode() != null) {
                currentNode = currentNode.getNextNode();
            }

            currentNode.setNextNode(newNode);
            newNode.setPreviousNode(currentNode);
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

        ListNode<E> newNode = new ListNode<>(element);
        ListNode<E> currentNode = this.head;

        if (index == 0) {
            this.head.setPreviousNode(newNode);
            newNode.setNextNode(this.head);
            this.head = newNode;
        } else {
            int i = 1;
            while (currentNode.getNextNode() != null && i <= index) {
                currentNode = currentNode.getNextNode();
                i++;
            }
            ListNode<E> previousNode = currentNode.getPreviousNode();
            previousNode.setNextNode(newNode);
            newNode.setPreviousNode(previousNode);
            newNode.setNextNode(currentNode);
            currentNode.setPreviousNode(newNode);
        }
        this.size++;
    }

    @Override
    public boolean contains(E element) {
        boolean elementIsFound = false;
        ListNode<E> currentNode = this.head;

        while (currentNode.getNextNode() != null && !elementIsFound) {
            if (currentNode.getData().equals(element)) {
                elementIsFound = true;
            } else {
                currentNode = currentNode.getNextNode();
            }
        }

        return elementIsFound;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException, IllegalStateException {
        if (this.isEmpty()) {
            throw new IllegalStateException("The List is Empty!");
        }
        if (index == 0) {
            return this.head.getData();
        }
        else if (isIndexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException("Index Out Of Bounds! =(");
        }

        ListNode<E> currentNode = this.head;
        for (int i = 1; i <= index; i++) {
            currentNode = currentNode.getNextNode();
        }
        return currentNode.getData();
    }

    @Override
    public int indexOf(E e) throws IllegalStateException {
        if (this.isEmpty()) {
            throw new IllegalStateException("The List is Empty!");
        }

        int indexOfElement = 0;
        ListNode<E> currentNode = this.head;

        while(currentNode.getNextNode() != null) {
            if (currentNode.getData().equals(e)) {
                break;
            }
            currentNode = currentNode.getNextNode();
            indexOfElement++;
        }

        return indexOfElement;
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
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

        ListNode<E> currentNode = this.head;
        E replacedElement = null;

        for (int i = 0; i <= index; i++) {
            if (i == index) {
                replacedElement = currentNode.getData();
                currentNode.setData(element);
            }
            currentNode = currentNode.getNextNode();
        }

        return replacedElement;
    }

    @Override
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    public void removingAtHead(ListNode<E> currentNode) {
        ListNode<E> nextNode = currentNode.getNextNode();
        nextNode.setPreviousNode(null);
        this.head = nextNode;
    }

    public void removingAtMiddle(ListNode<E> currentNode) {
        ListNode<E> previousNode = currentNode.getPreviousNode();
        ListNode<E> nextNode = currentNode.getNextNode();
        previousNode.setNextNode(nextNode);
        nextNode.setPreviousNode(previousNode);
    }

    public void removingAtEnd(ListNode<E> currentNode) {
        ListNode<E> previousNode = currentNode.getPreviousNode();
        previousNode.setNextNode(null);
    }

    @Override
    public Object remove(int index) {
        if (isIndexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException("Index Out of Bounds!");
        }

        ListNode<E> currentNode = this.head;
        ListNode<E> removedNode = null;

        if (index == 0) {
            removedNode = currentNode;
            removingAtHead(currentNode);
            return removedNode.getData();
        }

        for (int i = 1; i <= index; i++) {
            currentNode = currentNode.getNextNode();
            if (i == index) {
                if (currentNode.getNextNode() != null) {
                    removingAtMiddle(currentNode);
                } else {
                    removingAtEnd(currentNode);
                }
                removedNode = currentNode;
                break;
            }
        }
        this.size--;

        if (removedNode == null) {
            return null;
        }
        return removedNode.getData();
    }

    @Override
    public boolean remove(E e) {
        boolean elementWasRemoved = false;
        ListNode<E> currentNode = this.head;

        while (currentNode.getNextNode() != null) {
            if (currentNode.getData().equals(e)) {
                if (currentNode.getPreviousNode() == null) {
                    removingAtHead(currentNode);
                }
                if (currentNode.getNextNode() == null) {
                    removingAtEnd(currentNode);
                }
                if (currentNode.getNextNode() != null) {
                    removingAtMiddle(currentNode);
                }

                elementWasRemoved = true;
                this.size--;
                break;
            }
            currentNode = currentNode.getNextNode();
        }

        return elementWasRemoved;
    }

    public CustomListIterator<E> iterator() {
        return new CustomLinkedListIterator<>(this.head);
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "The List is Empty!";
        }
        StringBuilder listToPrint = new StringBuilder("[");
        ListNode<E> currentNode = this.head;

        while (currentNode.getNextNode() != null) {
            listToPrint.append(currentNode.getData().toString()).append(", ");
            currentNode = currentNode.getNextNode();
        }

        listToPrint.append(currentNode.getData().toString()).append(", ");
        listToPrint = new StringBuilder(listToPrint.substring(0, listToPrint.length() - 2) + "]");
        return listToPrint.toString();
    }

}
