package com.custom.collections.hashset;

import com.custom.collections.CustomListIterator;
import com.custom.collections.Set;
import com.custom.collections.linkedlist.CustomLinkedList;

import static java.lang.Math.abs;

public class CustomHashSet<E> implements Set<E> {
    private int size = 0;
    private static final int INITIAL_CAPACITY = 3, MAX_ELEMENTS_IN_LIST = 4;
    private CustomLinkedList<E>[] buckets;

    @SuppressWarnings("unchecked")
    private void initializeBuckets(int capacity) {
        buckets = new CustomLinkedList[capacity];
    }

    CustomHashSet() {
        initializeBuckets(INITIAL_CAPACITY);
    }

    CustomHashSet(int capacity) {
        initializeBuckets(capacity);
    }

    private int getHashCodeModulus(E element) {
        if (element == null) {
            return 0;
        }
        return abs(element.hashCode() % buckets.length);
    }

    @SuppressWarnings("unchecked")
    private void addBucketsCapacity() {
        int newCapacity = buckets.length * 2;
        CustomLinkedList<E>[] tempBucketsCopy = new CustomLinkedList[newCapacity];

        System.arraycopy(buckets, 0, tempBucketsCopy, 0, buckets.length);
        buckets = new CustomLinkedList[newCapacity];
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
    }

    @Override
    public boolean add(E element) {
        int hashCodeMod = getHashCodeModulus(element);
        boolean elementWasAdded = false;

        // TODO: validate the size of the list and simplify inner else if
        // Add element when the bucket does not have elements
        if (buckets[hashCodeMod] == null) {
            CustomLinkedList<E> newList = new CustomLinkedList<>();
            newList.add(element);
            buckets[hashCodeMod] = newList;
            elementWasAdded = true;
        } else if (!contains(element) && buckets[hashCodeMod].size() < MAX_ELEMENTS_IN_LIST) {
            buckets[hashCodeMod].add(element);
            elementWasAdded = true;
        } else if (!contains(element) && buckets[hashCodeMod].size() >= MAX_ELEMENTS_IN_LIST) {
            addBucketsCapacity();
            add(element);
        }

        if (elementWasAdded) {
            size++;
        }

        return elementWasAdded;
    }

    @Override
    public boolean contains(E element) {
        int hashCodeMod = getHashCodeModulus(element);
        boolean elementWasFound = false;

        if (buckets[hashCodeMod] != null) {
            CustomListIterator<E> iterator = buckets[hashCodeMod].iterator();

            while(iterator.hasNext() && !elementWasFound ) {
                E elementInList = iterator.next();
                if (element == null || element.equals(elementInList)) {
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

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        buckets = new CustomLinkedList[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public boolean remove(E e) throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("HashSet is empty!");
        }

        int hashCodeMod = getHashCodeModulus(e);

        CustomListIterator<E> iterator = buckets[hashCodeMod].iterator();
        boolean elementWasRemoved = false;

        while(iterator.hasNext()) {
            E elementInList = iterator.next();
            if (e.equals(elementInList)) {
                elementWasRemoved = buckets[hashCodeMod].remove(e);
            }
        }

        return elementWasRemoved;
    }

    @Override
    public CustomListIterator<E> iterator() {
        return new CustomHashSetIterator<>(buckets, size);
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
        return buckets.length;
    }
}
