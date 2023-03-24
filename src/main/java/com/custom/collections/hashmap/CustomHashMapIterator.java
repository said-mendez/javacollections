package com.custom.collections.hashmap;

import com.custom.collections.CustomIterator;
import com.custom.collections.linkedlist.CustomLinkedList;

public class CustomHashMapIterator<K,V> implements MapIterator<K,V> {
    private int bucketsCurrentIndex = 0, linkedListElementIndex = 0, numberOfElementsReturned = 0;
    private final int numberOfElements;
    private final CustomLinkedList<MapEntry<K, V>>[] buckets;
    private CustomLinkedList<MapEntry<K, V>> linkedList;
    private CustomIterator<MapEntry<K, V>> iterator;

    public CustomHashMapIterator(CustomLinkedList<MapEntry<K, V>>[] buckets, int size) {
        this.buckets = buckets;
        numberOfElements = size;
    }

    @Override
    public boolean hasNext() {
        return numberOfElementsReturned < numberOfElements;
    }

    @Override
    public MapEntry<K, V> next() {
        MapEntry<K, V> nextElement = null;

        if (buckets[bucketsCurrentIndex] == null && hasNext()) {
            bucketsCurrentIndex++;
            return next();
        }

        if (linkedList == null || linkedListElementIndex == linkedList.size() ) {
            linkedList = buckets[bucketsCurrentIndex];
            iterator = linkedList.iterator();
            linkedListElementIndex = 0;
        }

        if (iterator.hasNext()) {
            nextElement = iterator.next();
            linkedListElementIndex++;
            numberOfElementsReturned++;
        }

        return nextElement;
    }
}
