package com.custom.collections;

import java.util.Iterator;

public class CustomArrayList<E> implements CustomListInterface<E> {
    private int initialCapacity, size = 0;
    private static final int INITIAL_CAPACITY = 20;
    private Object [] arrayList;

    private void initializeArray() {
        this.arrayList = new Object [this.initialCapacity];
    }

    public CustomArrayList(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        initializeArray();
    }

    public CustomArrayList() {
        this.initialCapacity = INITIAL_CAPACITY;
        initializeArray();
    }

    private Object [] cloneArrayList() {
        Object [] auxArrayList = new Object [this.size];

        for (int i = 0; i < this.size; i++) {
            auxArrayList[i] = this.arrayList[i];
        }

        return auxArrayList;
    }

    private void addCapacity() {
        if (this.size == this.arrayList.length) {
            Object [] tempArrayList = cloneArrayList();

            // Increase capacity 50% more than the previous one
            int newCapacity = (int) (this.size * 1.5);
            this.arrayList = new Object [newCapacity];

            for (int i = 0; i < this.size; i++) {
                this.arrayList[i] = tempArrayList[i];
            }
        }
    }

    public boolean isIndexOutOfBounds(int index) {
        if (index < 0  || index >= this.size) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean add(E element) {
        addCapacity();
        this.arrayList[this.size++] = element;
        return true;
    }

    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException {
        System.out.println(index);
        if (this.size == 0 || index == this.size) {
            add(element);
        } else if (!isIndexOutOfBounds(index)) {
            addCapacity();
            Object [] tempArrayList = cloneArrayList();

            int tempIndex = 0;
            this.arrayList = new Object [this.arrayList.length];

            for (int i = 0; i <= tempArrayList.length; i++) {
                if (i == index) {
                    this.arrayList[i] = element;
                    this.arrayList[i+1] = tempArrayList[i++];
                    this.size++;
                } else {
                    this.arrayList[i] = tempArrayList[tempIndex];
                }
                tempIndex++;
            }
        } else {
            throw new IndexOutOfBoundsException("Index Out Of Bounds!!");
        }
    }

    @Override
    public boolean contains(Object o) {
        boolean elementIsFound = false;
        int index = 0;

        while(!elementIsFound && index < this.size) {
            if (this.arrayList[index].equals(o)) {
                elementIsFound = true;
            }
            index++;
        }

        return elementIsFound;
    }

    @Override
    public Object get(int index) throws IndexOutOfBoundsException {
        if (isIndexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException("Index Out Of Bounds!!");
        }

        return this.arrayList[index];
    }

    @Override
    public int indexOf(Object o) throws IllegalStateException{
        if (this.isEmpty()) {
            throw new IllegalStateException("Array List is Empty!");
        }
        int elementAtIndex = -1;

        for(int i = 0; i <  this.size; i++) {
            if (this.arrayList[i].equals(o)) {
                elementAtIndex = i;
            }
        }

        return elementAtIndex;
    }

    @Override
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }

        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public E set(int index, E element) throws IndexOutOfBoundsException, IllegalStateException {
        if (this.isEmpty()) {
            throw new IllegalStateException("Array List is Empty!");
        }
        if (isIndexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException("Index out of Bounds");
        }

        Object previousElement = this.arrayList[index];
        this.arrayList[index] = element;

        return (E) previousElement;
    }

    @Override
    public void clear() {
        this.arrayList = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    @Override
    public Object remove(int index) throws IllegalStateException, IndexOutOfBoundsException {
        if (this.isEmpty()) {
            throw new IllegalStateException("Array List is Empty!");
        }
        if (isIndexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException("Index Out Of Bounds!!");
        }

        Object removedElement = this.arrayList[index];
        Object [] tempArrayList = cloneArrayList();
        this.arrayList = new Object [this.arrayList.length];
        this.size = 0;

        for (int i = 0; i < tempArrayList.length; i++) {
            if (i != index) {
                this.add((E) tempArrayList[i]);
            }
        }

        return removedElement;
    }

    @Override
    public boolean remove(Object o) throws IllegalStateException {
        if (this.isEmpty()) {
            throw new IllegalStateException("Array List is Empty!");
        }

        if (indexOf(o) == -1) {
            return false;
        }

        Object [] tempArrayList = cloneArrayList();
        this.arrayList = new Object [this.arrayList.length];
        this.size = 0;

        for (int i = 0; i < tempArrayList.length; i++) {
            if (!tempArrayList[i].equals(o)) {
                this.add((E) tempArrayList[i]);
            }
        }

        return true;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    public CustomListIterator<E> iterator() {
        return new CustomArrayListIterator<E>(this, this.size);
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "The Array is Empty!";
        }
        String listToPrint = "[";

        for (int i = 0; i < this.size; i++) {
            listToPrint += this.arrayList[i].toString() + ", ";
        }

        listToPrint = listToPrint.substring(0, listToPrint.length() - 2) + "]";
        return listToPrint;
    }
}