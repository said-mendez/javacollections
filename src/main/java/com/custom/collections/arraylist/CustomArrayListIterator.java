package com.custom.collections.arraylist;

import com.custom.collections.CustomListIterator;

class CustomArrayListIterator<E> implements CustomListIterator<E> {
    private int currentIndex = 0;
    private final int size;
    private final CustomArrayList<E> arrayList;

    public CustomArrayListIterator(CustomArrayList<E> arrayList, int size) {
        this.arrayList = arrayList;
        this.size = size;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < size;
    }

    @Override
    public E next() {
        return arrayList.get(currentIndex++);
    }
}