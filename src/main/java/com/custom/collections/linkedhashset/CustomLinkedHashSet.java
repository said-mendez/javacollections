package com.custom.collections.linkedhashset;

import com.custom.collections.CustomIterator;
import com.custom.collections.hashset.CustomHashSet;
import com.custom.collections.linkedlist.CustomLinkedList;

public class CustomLinkedHashSet<E> extends CustomHashSet<E> {
    private CustomLinkedList<E> linkedList = new CustomLinkedList<>();

    CustomLinkedHashSet(int capacity) {
        super(capacity);
    }

    CustomLinkedHashSet() {
        super();
    }

    @Override
    public boolean add(E element) {
        boolean elementWasAdded;
        elementWasAdded = super.add(element);

        if (elementWasAdded) {
            elementWasAdded = linkedList.add(element);
        }

        return elementWasAdded;
    }

    @Override
    public boolean remove(E e) throws IllegalStateException {
        return super.remove(e) && linkedList.remove(e);
    }

    @Override
    public void clear() {
        super.clear();
        linkedList = new CustomLinkedList<>();
    }

    @Override
    public CustomIterator<E> iterator() {
        return linkedList.iterator();
    }

    @Override
    public String toString(){
        return linkedList.toString();
    }
}
