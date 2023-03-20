package com.custom.collections.hashset;

import com.custom.collections.CustomListIterator;
import com.custom.collections.linkedlist.CustomLinkedList;

public class CustomHashSetIterator<E> implements CustomListIterator<E> {
    private int arrayCurrentIndex = 0, linkedListElementIndex = 0, numberOfElementsReturned = 0;
    private final int numberOfElements;
    private final CustomLinkedList<E>[] buckets;

    public CustomHashSetIterator(CustomLinkedList<E>[] buckets, int size) {
        this.buckets = buckets;
        numberOfElements = size;
    }

    @Override
    public boolean hasNext() {
        return numberOfElementsReturned < numberOfElements;
    }

    @Override
    public E next() {
        E nextElement;

        if (buckets[arrayCurrentIndex] == null && hasNext()) {
            arrayCurrentIndex++;
            return next();
        }

        // TODO: Change structure to ArrayList
        CustomLinkedList<E> linkedList = buckets[arrayCurrentIndex];

        nextElement = linkedList.get(linkedListElementIndex);
        linkedListElementIndex++;
        numberOfElementsReturned++;

        // When we reach the last element in the list, then we can pass to the next index in the buckets array
        if (linkedListElementIndex == linkedList.size()) {
            arrayCurrentIndex++;
            // Reset linkedListElementIndex as we will start with a new list
            linkedListElementIndex = 0;
        }

        return nextElement;
    }
}
