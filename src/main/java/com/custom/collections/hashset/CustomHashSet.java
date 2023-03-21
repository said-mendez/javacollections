package com.custom.collections.hashset;

import com.custom.collections.CustomIterator;
import com.custom.collections.Set;
import com.custom.collections.arraylist.CustomArrayList;

import static java.lang.Math.abs;

public class CustomHashSet<E> implements Set<E> {
    private int size = 0;
    private static final int INITIAL_CAPACITY = 3, MAX_ELEMENTS_IN_LIST = 4;
    private CustomArrayList<E>[] buckets;

    @SuppressWarnings("unchecked")
    private void initializeBuckets(int capacity) {
        buckets = new CustomArrayList[capacity];
    }

    public CustomHashSet() {
        initializeBuckets(INITIAL_CAPACITY);
    }

    public CustomHashSet(int capacity) {
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
        CustomArrayList<E>[] tempBucketsCopy = new CustomArrayList[newCapacity];

        System.arraycopy(buckets, 0, tempBucketsCopy, 0, buckets.length);
        buckets = new CustomArrayList[newCapacity];
        size = 0;

        // Loop through each element in tempBucketCopy and assign it to buckets with the new hashCode
        // We have a O(M*N) where M is the capacity and N is the list length
        for (CustomArrayList<E> arrayList : tempBucketsCopy) {
            if (arrayList != null) {
                CustomIterator<E> iterator = arrayList.iterator();
                while(iterator.hasNext()) {
                    addRecursive(iterator.next());
                }
            }
        }
    }

    public boolean addRecursive(E element) {
        int hashCodeMod = getHashCodeModulus(element);
        boolean elementWasAdded = false;

        // Add element when the bucket does not have elements
        if (buckets[hashCodeMod] == null) {
            CustomArrayList<E> newArray = new CustomArrayList<>();
            newArray.add(element);
            buckets[hashCodeMod] = newArray;
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
    public boolean add(E element) {

//        // Add element when the bucket does not have elements
//        if (buckets[hashCodeMod] == null) {
//            CustomArrayList<E> newArray = new CustomArrayList<>();
//            newArray.add(element);
//            buckets[hashCodeMod] = newArray;
//            elementWasAdded = true;
//        } else if (!contains(element) && buckets[hashCodeMod].size() < MAX_ELEMENTS_IN_LIST) {
//            buckets[hashCodeMod].add(element);
//            elementWasAdded = true;
//        } else if (!contains(element) && buckets[hashCodeMod].size() >= MAX_ELEMENTS_IN_LIST) {
//            addBucketsCapacity();
//            add(element);
//        }
//
//        if (elementWasAdded) {
//            size++;
//        }

        return addRecursive(element);
    }

    @Override
    public boolean contains(E element) {
        int hashCodeMod = getHashCodeModulus(element);
        boolean elementWasFound = false;

        if (buckets[hashCodeMod] != null) {
            CustomIterator<E> iterator = buckets[hashCodeMod].iterator();

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
        buckets = new CustomArrayList[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public boolean remove(E e) throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("HashSet is empty!");
        }

        int hashCodeMod = getHashCodeModulus(e);

        CustomIterator<E> iterator = buckets[hashCodeMod].iterator();
        boolean elementWasRemoved = false;

        while(iterator.hasNext() && !elementWasRemoved) {
            E elementInList = iterator.next();

            if (e.equals(elementInList)) {
                elementWasRemoved = buckets[hashCodeMod].remove(e);
            }
        }

        if (elementWasRemoved) {
            size--;
        }

        return elementWasRemoved;
    }

    @Override
    public CustomIterator<E> iterator() {
        return new CustomHashSetIterator<>(buckets, size);
    }

    @Override
    public String toString(){
        if (this.isEmpty()) {
            return "[]";
        }

        StringBuilder completeHashSet = new StringBuilder();
        int bucketNumber = 0;

        for (CustomArrayList<E> arrayList : buckets) {
            StringBuilder listToPrint = new StringBuilder("[");
            String bucketHeader = "\nBucket: " + bucketNumber + "\n";
            completeHashSet.append(bucketHeader);

            if (arrayList != null) {
                CustomIterator<E> iterator = arrayList.iterator();
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
