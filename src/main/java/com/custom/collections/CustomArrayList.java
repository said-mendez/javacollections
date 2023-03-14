package com.custom.collections;

public class CustomArrayList<E> implements CustomListInterface<E> {
    private final int initialCapacity;
    private int size = 0;
    private static final int INITIAL_CAPACITY = 20;
    private E [] arrayList;

    @SuppressWarnings("unchecked")
    private void initializeArray() {
        this.arrayList = (E[]) new Object [this.initialCapacity];
    }

    public CustomArrayList(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        initializeArray();
    }

    public CustomArrayList() {
        this.initialCapacity = INITIAL_CAPACITY;
        initializeArray();
    }

    @SuppressWarnings("unchecked")
    private E [] cloneArrayList() {
        E [] auxArrayList = (E[]) new Object [this.size];

        System.arraycopy(this.arrayList, 0, auxArrayList, 0, this.size);

        return auxArrayList;
    }

    @SuppressWarnings("unchecked")
    private void addCapacity() {
        if (this.size == this.arrayList.length) {
            E [] tempArrayList = cloneArrayList();

            // Increase capacity 50% more than the previous one
            int newCapacity = (int) (this.size * 1.5);
            this.arrayList = (E[]) new Object [newCapacity];

            System.arraycopy(this.arrayList, 0, tempArrayList, 0, this.size);
        }
    }

    public boolean isIndexOutOfBounds(int index) {
        return index < 0 || index >= this.size;
    }

    @Override
    public boolean add(E element) {
        addCapacity();
        this.arrayList[this.size++] = element;
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void add(int index, E element) throws IndexOutOfBoundsException {
        System.out.println(index);
        if (this.size == 0 || index == this.size) {
            add(element);
        } else if (!isIndexOutOfBounds(index)) {
            addCapacity();
            E [] tempArrayList = cloneArrayList();

            int tempIndex = 0;
            this.arrayList = (E[]) new Object [this.arrayList.length];

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
    public boolean contains(E e) {
        boolean elementIsFound = false;
        int index = 0;

        while(!elementIsFound && index < this.size) {
            if (this.arrayList[index].equals(e)) {
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

        return this.arrayList[index];
    }

    @Override
    public int indexOf(E e) throws IllegalStateException{
        if (this.isEmpty()) {
            throw new IllegalStateException("Array List is Empty!");
        }
        int elementAtIndex = -1;

        for(int i = 0; i <  this.size; i++) {
            if (this.arrayList[i].equals(e)) {
                elementAtIndex = i;
            }
        }

        return elementAtIndex;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
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

        E previousElement = this.arrayList[index];
        this.arrayList[index] = element;

        return previousElement;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        this.arrayList = (E[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
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
        this.arrayList = (E[]) new Object [this.arrayList.length];
        this.size = 0;

        for (int i = 0; i < tempArrayList.length; i++) {
            if (i != index) {
                this.add((E) tempArrayList[i]);
            }
        }

        return removedElement;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean remove(E e) throws IllegalStateException {
        if (this.isEmpty()) {
            throw new IllegalStateException("Array List is Empty!");
        }

        if (indexOf(e) == -1) {
            return false;
        }

        E [] tempArrayList = cloneArrayList();
        this.arrayList = (E[]) new Object [this.arrayList.length];
        this.size = 0;

        for (E element : tempArrayList) {
            if (!element.equals(e)) {
                this.add(element);
            }
        }

        return true;
    }

    public CustomListIterator<E> iterator() {
        return new CustomArrayListIterator<>(this, this.size);
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "The Array is Empty!";
        }
        StringBuilder listToPrint = new StringBuilder("[");

        for (int i = 0; i < this.size; i++) {
            listToPrint.append(this.arrayList[i].toString()).append(", ");
        }

        listToPrint = new StringBuilder(listToPrint.substring(0, listToPrint.length() - 2) + "]");
        return listToPrint.toString();
    }
}