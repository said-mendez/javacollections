package com.custom.collections.hashmap;

import com.custom.collections.CustomIterator;
import com.custom.collections.CustomMap;
import com.custom.collections.linkedlist.CustomLinkedList;

import java.util.Map;
import java.util.function.BiFunction;

import static java.lang.Math.abs;

public class CustomHashMap<K, V> implements CustomMap<K, V> {
    private int size = 0;
    private static final int INITIAL_CAPACITY = 3, MAX_ELEMENTS_IN_LIST = 4;
    private CustomLinkedList<MapEntry<K, V>>[] buckets;

    private void initializeBuckets(int capacity) { buckets = new CustomLinkedList[capacity]; }

    public CustomHashMap(int capacity) {
        initializeBuckets(capacity);
    }

    public CustomHashMap() {
        initializeBuckets(INITIAL_CAPACITY);
    }

    private int getHashCodeModulus(K key) {
        if (key == null) {
            return 0;
        }
        return abs(key.hashCode() % buckets.length);
    }

    private void addBucketsCapacity() {
        int newCapacity = buckets.length * 2;
        CustomLinkedList<MapEntry<K, V>>[] tempBucketsCopy = new CustomLinkedList[newCapacity];

        System.arraycopy(buckets, 0, tempBucketsCopy, 0, buckets.length);
        buckets = new CustomLinkedList[newCapacity];

        for (CustomLinkedList<MapEntry<K, V>> linkedList : tempBucketsCopy) {
            if (linkedList != null) {
                CustomIterator<MapEntry<K, V>> iterator = linkedList.iterator();
                while(iterator.hasNext()) {
                    MapEntry<K, V> entry = iterator.next();
                    putRecursive(entry.key, entry.value);
                }
            }
        }
    }

    @Override
    public void clear() {

    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int hashCodeMod = getHashCodeModulus(key);
        boolean keyWasFound = false;

        if (buckets[hashCodeMod] != null) {
            CustomIterator<MapEntry<K, V>> iterator = buckets[hashCodeMod].iterator();

            while (iterator.hasNext() && !keyWasFound) {
                MapEntry<K, V> entry = iterator.next();
                if (entry == null || entry.key.equals(key)) {
                    keyWasFound = true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        return false;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V put(K key, V value) {
        return putRecursive(key, value);
    }

    public V putRecursive(K key, V value) {
        int hashCodeMod = getHashCodeModulus(key);
        boolean elementWasAdded = false;
        MapEntry<K, V> newEntry = new MapEntry(key, value);

        if (buckets[hashCodeMod] == null) {
            CustomLinkedList<MapEntry<K, V>> linkedList = new CustomLinkedList<>();
            linkedList.add(newEntry);
            elementWasAdded = true;
        } else if (!containsKey(key) && buckets[hashCodeMod].size() < MAX_ELEMENTS_IN_LIST) {
            buckets[hashCodeMod].add(newEntry);
            elementWasAdded = true;
        } else if (!containsKey(key) && buckets[hashCodeMod].size() >= MAX_ELEMENTS_IN_LIST) {
            addBucketsCapacity();
            put(key, value);
        }

        if (elementWasAdded) {
            size++;
        }

        return value;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public boolean remove(K key, V value) {
        return false;
    }

    @Override
    public V replace(K key, V value) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }
}
