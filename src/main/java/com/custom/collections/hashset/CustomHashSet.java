package com.custom.collections.hashset;

import com.custom.collections.CustomListIterator;
import com.custom.collections.Set;
import com.custom.collections.linkedlist.CustomLinkedList;

import java.util.Objects;

import static java.lang.Math.abs;

public class CustomHashSet<E> implements Set<E> {
    private int size = 0, capacity;
    private static final int INITIAL_CAPACITY = 3, MAX_ELEMENTS_IN_LIST = 4;
    private CustomLinkedList<E>[] buckets;
    //  TODO: I've created the hashCode using the entryValue property, is this OK?
    private E entryValue;

    // TODO: delete capacity
    private void initializeBuckets(int capacity) {
        buckets = new CustomLinkedList[capacity];
        this.capacity = capacity;
    }

    CustomHashSet() {
        initializeBuckets(INITIAL_CAPACITY);
    }

    CustomHashSet(int capacity) {
        initializeBuckets(capacity);
    }

    private int getHashCodeModulus() {
        return abs(hashCode() % capacity);
    }

    private void addBucketsCapacity() {
        E pendingValueToAdd = entryValue;
        capacity *= 2;
        CustomLinkedList<E>[] tempBucketsCopy = new CustomLinkedList[capacity];

        System.arraycopy(buckets, 0, tempBucketsCopy, 0, buckets.length);
        buckets = new CustomLinkedList[capacity];
        size = 0;

        // Loop through each element in tempBucketCopy and assign it to buckets with the new hashCode
        // We have a O(M*N) where M is the capacity and N is the list length
        for (CustomLinkedList<E> linkedList : tempBucketsCopy) {
            if (linkedList != null) {
                CustomListIterator<E> iterator = linkedList.iterator();
                while(iterator.hasNext()) {
                    add(iterator.next());
                }
            }
        }
        add(pendingValueToAdd);
    }

    @Override
    public boolean add(E element) {
        entryValue = element;
        int hashCodeMod = getHashCodeModulus();
        boolean elementWasAdded = false;

        // TODO: validate the size of the list and simplify inner else if
        // Add element when the bucket does not have elements
        if (buckets[hashCodeMod] == null) {
            CustomLinkedList<E> newList = new CustomLinkedList<>();
            newList.add(entryValue);
            buckets[hashCodeMod] = newList;
            elementWasAdded = true;
        }
        // Add when bucket already have elements
        if (!contains(entryValue) && buckets[hashCodeMod].size() < MAX_ELEMENTS_IN_LIST) {
            buckets[hashCodeMod].add(entryValue);
            elementWasAdded = true;
        }
        if (!contains(entryValue) && buckets[hashCodeMod].size() >= MAX_ELEMENTS_IN_LIST) {
            addBucketsCapacity();
        }

        if (elementWasAdded) {
            size++;
        }

        return elementWasAdded;
    }

    @Override
    public boolean contains(E element) {
        entryValue = element;
        int hashCodeMod = getHashCodeModulus();
        boolean elementWasFound = false;

        if (buckets[hashCodeMod] != null) {
            // TODO: Checking if the element exists directly in the list is the same as using class equals?
            CustomListIterator<E> iterator = buckets[hashCodeMod].iterator();

            while(iterator.hasNext() && !elementWasFound ) {
                E elementInList = iterator.next();
                if (entryValue == null || entryValue.equals(elementInList)) {
                    elementWasFound = true;
                }
            }
        }

        return elementWasFound;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    // TODO: If we reference buckets to a new object will be garbage collected?
    @Override
    public void clear() {
        capacity = INITIAL_CAPACITY;
        buckets = new CustomLinkedList[capacity];
        size = 0;
    }

    @Override
    public boolean remove(E e) throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("HashSet is empty!");
        }
        entryValue = e;
        int hashCodeMod = getHashCodeModulus();

        CustomListIterator<E> iterator = buckets[hashCodeMod].iterator();
        boolean elementWasRemoved = false;

        while(iterator.hasNext()) {
            E elementInList = iterator.next();
            if (entryValue.equals(elementInList)) {
                elementWasRemoved = buckets[hashCodeMod].remove(e);
            }
        }

        return elementWasRemoved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomHashSet<?> that = (CustomHashSet<?>) o;

        return Objects.equals(entryValue, that.entryValue);
    }

    @Override
    public CustomListIterator<E> iterator() {
        return new CustomHashSetIterator<>(buckets, size);
    }

    // TODO: delete hashcode in hashset
    @Override
    public int hashCode() {
        return entryValue != null ? entryValue.hashCode() : 0;
    }

    @Override
    public String toString() throws IllegalStateException{
        if (this.isEmpty()) {
            throw new IllegalStateException("The List is Empty!");
        }

        StringBuilder completeHashSet = new StringBuilder();
        int bucketNumber = 0;

        for (CustomLinkedList<E> linkedList : buckets) {
            StringBuilder listToPrint = new StringBuilder("[");
            String bucketHeader = "\nBucket: " + bucketNumber + "\n";
            completeHashSet.append(bucketHeader);

            if (linkedList != null) {
                CustomListIterator<E> iterator = linkedList.iterator();
                while(iterator.hasNext()) {
                    listToPrint.append(iterator.next()).append(", ");
                }
            }

            if (listToPrint.length() > 1) {
                listToPrint = new StringBuilder(listToPrint.substring(0, listToPrint.length() - 2) + "]");
                completeHashSet.append(listToPrint);
            }
            bucketNumber++;
        }

        return completeHashSet.toString();
    }

    public int getCapacity() {
        return capacity;
    }
}
