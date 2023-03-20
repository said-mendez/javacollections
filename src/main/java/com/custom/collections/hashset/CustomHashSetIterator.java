package com.custom.collections.hashset;

import com.custom.collections.CustomIterator;
import com.custom.collections.arraylist.CustomArrayList;

public class CustomHashSetIterator<E> implements CustomIterator<E> {
    private int arrayCurrentIndex = 0, linkedListElementIndex = 0, numberOfElementsReturned = 0;
    private final int numberOfElements;
    private final CustomArrayList<E>[] buckets;

    public CustomHashSetIterator(CustomArrayList<E>[] buckets, int size) {
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

        CustomArrayList<E> arrayList = buckets[arrayCurrentIndex];

        nextElement = arrayList.get(linkedListElementIndex);
        linkedListElementIndex++;
        numberOfElementsReturned++;

        // When we reach the last element in the list, then we can pass to the next index in the buckets array
        if (linkedListElementIndex == arrayList.size()) {
            arrayCurrentIndex++;
            // Reset linkedListElementIndex as we will start with a new list
            linkedListElementIndex = 0;
        }

        return nextElement;
    }
}
