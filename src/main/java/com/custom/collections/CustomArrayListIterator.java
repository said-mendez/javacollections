package com.custom.collections;

public class CustomArrayListIterator<E> implements CustomListIterator {
    private int currentIndex = 0, size;
    private CustomArrayList arrayList;

    public CustomArrayListIterator(CustomArrayList arrayList, int size) {
        this.arrayList = arrayList;
        this.size = size;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < this.size;
    }

    @Override
    public E next() {
        return (E) arrayList.get(currentIndex++);
    }
}
