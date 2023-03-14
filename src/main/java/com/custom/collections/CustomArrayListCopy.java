package com.custom.collections;

public class CustomArrayListCopy<E> implements CustomListInterface {
    private int initialCapacity, size = 0;
    private static final int INITIAL_CAPACITY = 20;
    private Object [] arrayList;

    private void initializeArray() {
        this.arrayList = new Object [this.initialCapacity];
    }

    public CustomArrayListCopy(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        initializeArray();
    }

    public CustomArrayListCopy() {
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
    public boolean add(Object o) {
        addCapacity();
        this.arrayList[this.size++] = o;
        return true;
    }

    @Override
    public void add(int index, Object element) {
        System.out.println("Size: " + this.size + " Length: " + this.arrayList.length);
        if (index == this.size) {
            add(element);
        }
        else if (!isIndexOutOfBounds(index)) {
            addCapacity();
            System.out.println("Size: " + this.size + " Length: " + this.arrayList.length);
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
        }
    }

    @Override
    public boolean contains(Object o) {
        boolean elementIsFound = false;
        int index = 0;

        while(!elementIsFound && index < this.size) {
            if (this.arrayList[index] == o) {
                elementIsFound = true;
            }
            index++;
        }

        return elementIsFound;
    }

    @Override
    public Object get(int index) {
        if (isIndexOutOfBounds(index)) {
            return "Index out of bounds";
        }

        return this.arrayList[index];
    }

    @Override
    public int indexOf(Object o) {
        int elementAtIndex = -1;

        for(int i = 0; i <  this.size; i++) {
            if (this.arrayList[i] == o) {
                elementAtIndex = i;
            }
        }

        return elementAtIndex;
    }

    @Override
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Object set(int index, Object element) {
        if (!isIndexOutOfBounds(index)) {
            Object previousElement = this.arrayList[index];
            this.arrayList[index] = element;
            return previousElement;
        }

        return "Index out of Bounds";
    }

    @Override
    public void clear() {

    }

    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }
}