package com.custom.collections.arraylist;

import com.custom.collections.CustomListInterface;
import com.custom.collections.CustomListIterator;

public class CustomArrayList<E> implements CustomListInterface<E> {
    private int size = 0;
    private static final int INITIAL_CAPACITY = 20;
    private E [] arrayList;

    @SuppressWarnings("unchecked")
    private void initializeArray(int initialCapacity) {
        arrayList = (E[]) new Object [initialCapacity];
    }

    public CustomArrayList(int initialCapacity) {
        initializeArray(initialCapacity);
    }

    public CustomArrayList() {
        initializeArray(INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    private E [] cloneArrayList() {
        E [] auxArrayList = (E[]) new Object [size];

        System.arraycopy(arrayList, 0, auxArrayList, 0, size);

        return auxArrayList;
    }

    @SuppressWarnings("unchecked")
    private void addCapacity() {
        if (size == arrayList.length) {
            // Increase capacity 50% more than the previous one
            int newCapacity = size * 2;
            E[] tempArrayList = (E[]) new Object [newCapacity];

            System.arraycopy(arrayList, 0, tempArrayList, 0, size);
            arrayList = tempArrayList;
        }
    }

    public boolean isIndexOutOfBounds(int index) {
        return index < 0 || index >= size;
    }

    @Override
    public boolean add(E element) {
        addCapacity();
        arrayList[size++] = element;
        return true;
    }

    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException {
        if (size == 0 || index == size) {
            add(element);
        }
        if (isIndexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException("Index Out Of Bounds!!");
        }
        addCapacity();

        for (int i = size; i > index; i--) {
            arrayList[i] = arrayList[i-1];
        }
        arrayList[index] = element;
    }

    @Override
    public boolean contains(E e) {
        boolean elementIsFound = false;
        int index = 0;

        while(!elementIsFound && index < size) {
            if (arrayList[index].equals(e)) {
                elementIsFound = true;
            }
            index++;
        }

        return elementIsFound;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (isIndexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException("Index Out Of Bounds!!");
        }

        return arrayList[index];
    }

    @Override
    public int indexOf(E e) throws IllegalStateException{
        if (isEmpty()) {
            throw new IllegalStateException("Array List is Empty!");
        }
        int elementAtIndex = -1;

        for(int i = 0; i <  size; i++) {
            if (arrayList[i].equals(e)) {
                elementAtIndex = i;
            }
        }

        return elementAtIndex;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E set(int index, E element) throws IndexOutOfBoundsException, IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Array List is Empty!");
        }
        if (isIndexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException("Index out of Bounds");
        }

        E previousElement = arrayList[index];
        arrayList[index] = element;

        return previousElement;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        arrayList = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E remove(int index) throws IllegalStateException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new IllegalStateException("Array List is Empty!");
        }
        if (isIndexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException("Index Out Of Bounds!!");
        }

        E removedElement = arrayList[index];
        E [] tempArrayList = cloneArrayList();
        arrayList = (E[]) new Object [arrayList.length];
        size = 0;

        for (int i = 0; i < tempArrayList.length; i++) {
            if (i != index) {
                add(tempArrayList[i]);
            }
        }

        return removedElement;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean remove(E e) throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Array List is Empty!");
        }

        if (indexOf(e) == -1) {
            return false;
        }

        E [] tempArrayList = cloneArrayList();
        arrayList = (E[]) new Object [arrayList.length];
        size = 0;

        for (E element : tempArrayList) {
            if (!element.equals(e)) {
                add(element);
            }
        }

        return true;
    }

    public CustomListIterator<E> iterator() {
        return new CustomArrayListIterator<>(this, size);
    }

    @Override
    public String toString() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("ArrayList is Empty!");
        }
        StringBuilder listToPrint = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            listToPrint.append(arrayList[i].toString()).append(", ");
        }

        listToPrint = new StringBuilder(listToPrint.substring(0, listToPrint.length() - 2) + "]");
        return listToPrint.toString();
    }
}