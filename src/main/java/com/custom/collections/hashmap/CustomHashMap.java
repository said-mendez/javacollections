package com.custom.collections.hashmap;

import com.custom.collections.CustomIterator;
import com.custom.collections.CustomMap;
import com.custom.collections.linkedlist.CustomLinkedList;
import java.util.function.BiFunction;

import static java.lang.Math.abs;

public class CustomHashMap<K, V> implements CustomMap<K, V> {
    private int size = 0;
    private static final int INITIAL_CAPACITY = 3, MAX_ELEMENTS_IN_LIST = 4;
    private CustomLinkedList<MapEntry<K, V>>[] buckets;

    @SuppressWarnings("unchecked")
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

    @SuppressWarnings("unchecked")
    private void addBucketsCapacity() {
        int newCapacity = buckets.length * 2;
        CustomLinkedList<MapEntry<K, V>>[] tempBucketsCopy = new CustomLinkedList[newCapacity];

        System.arraycopy(buckets, 0, tempBucketsCopy, 0, buckets.length);
        buckets = new CustomLinkedList[newCapacity];
        size = 0;

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

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        buckets = new CustomLinkedList[INITIAL_CAPACITY];
        size = 0;
    }

    // TODO: Is necessary to delete the key if null?
    // int newPrice = prices.compute("Shoes", (key, value) -> value - value * 10/100);
    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        V oldValue = null;
        MapEntry<K, V> entry = getMapEntry(key);

        if (entry != null) {
            oldValue = entry.value;

            V newValue = remappingFunction.apply(key, oldValue);
            if (oldValue != null) {
                if (newValue != null) {
                    entry.value = newValue;
                }
                else {
                    remove(key);
                }
            } else {
                if (newValue != null) {
                    entry.value = newValue;
                } else {
                    return null;
                }
            }
        }

        return oldValue;
    }

    @Override
    public boolean containsKey(K key) {
        int hashCodeMod = getHashCodeModulus(key);
        boolean keyWasFound = false;

        if (buckets[hashCodeMod] != null) {
            CustomIterator<MapEntry<K, V>> iterator = buckets[hashCodeMod].iterator();

            while (iterator.hasNext() && !keyWasFound) {
                MapEntry<K, V> entry = iterator.next();
                if (entry.key == null || entry.key.equals(key)) {
                    keyWasFound = true;
                }
            }
        }

        return keyWasFound;
    }

    @Override
    public boolean containsValue(V value) {
        boolean valueWasFound = false;

        for (CustomLinkedList<MapEntry<K, V>> linkedList : buckets) {
            if (linkedList != null) {
                CustomIterator<MapEntry<K, V>> iterator = linkedList.iterator();

                while(iterator.hasNext() && !valueWasFound) {
                    MapEntry<K, V> entry = iterator.next();
                    if (value.equals(entry.value)) {
                        valueWasFound = true;
                    }
                }
            }

            if (valueWasFound) {
                break;
            }
        }

        return valueWasFound;
    }

    private MapEntry<K, V> getMapEntry(K key) {
        if (isEmpty()) {
            return null;
        }

        boolean keyWasFound = false;
        MapEntry<K, V> mapEntry = null;
        int hashCodeMod = getHashCodeModulus(key);

        if (buckets[hashCodeMod] != null) {
            CustomIterator<MapEntry<K, V>> iterator = buckets[hashCodeMod].iterator();

            while (iterator.hasNext() && !keyWasFound) {
                MapEntry<K, V> entry = iterator.next();
                if (entry.key == null || entry.key.equals(key)) {
                    mapEntry = entry;
                    keyWasFound = true;
                }
            }
        }

        return mapEntry;
    }

    @Override
    public V get(K key) {
        int hashCodeMod = getHashCodeModulus(key);
        boolean keyWasFound = false;
        V value = null;

        if (buckets[hashCodeMod] != null) {
            CustomIterator<MapEntry<K, V>> iterator = buckets[hashCodeMod].iterator();

            while (iterator.hasNext() && !keyWasFound) {
                MapEntry<K, V> entry = iterator.next();
                if (entry.key == null || entry.key.equals(key)) {
                    value = entry.value;
                    keyWasFound = true;
                }
            }
        }

        return value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private V putRecursive(K key, V value) {
        int hashCodeMod = getHashCodeModulus(key);
        boolean elementWasAdded = false;
        MapEntry<K, V> newEntry = new MapEntry<>(key, value);

        if (buckets[hashCodeMod] == null) {
            CustomLinkedList<MapEntry<K, V>> linkedList = new CustomLinkedList<>();
            linkedList.add(newEntry);
            buckets[hashCodeMod] = linkedList;
            elementWasAdded = true;
        } else if (!containsKey(key) && buckets[hashCodeMod].size() < MAX_ELEMENTS_IN_LIST) {
            buckets[hashCodeMod].add(newEntry);
            elementWasAdded = true;
        } else if (!containsKey(key) && buckets[hashCodeMod].size() >= MAX_ELEMENTS_IN_LIST) {
            addBucketsCapacity();
            put(key, value);
        } else if (containsKey(key)) {
            MapEntry<K, V> entry = getMapEntry(key);
            entry.value = value;
        }

        if (elementWasAdded) {
            size++;
        }

        return value;
    }

    @Override
    public V put(K key, V value) {
        return putRecursive(key, value);
    }

    @Override
    public V remove(K key) {
        int hashCodeMod = getHashCodeModulus(key);
        V removedValue = null;

        if (containsKey(key)) {
            CustomLinkedList<MapEntry<K, V>> linkedList = buckets[hashCodeMod];
            CustomIterator<MapEntry<K, V>> iterator = linkedList.iterator();

            while (iterator.hasNext()) {
                MapEntry<K, V> currentEntry = iterator.next();
                if (currentEntry.key == key) {
                    removedValue = currentEntry.value;
                    linkedList.remove(currentEntry);
                    size--;
                    break;
                }
            }
        }

        return removedValue;
    }

    @Override
    public boolean remove(K key, V value) {
        boolean elementWasRemoved = false;
        MapEntry<K, V> entry = getMapEntry(key);

        if (entry != null && entry.value == value) {
            V removedValue = remove(key);
            if (removedValue != null) {
                elementWasRemoved = true;
            }

        }

        return elementWasRemoved;
    }

    @Override
    public V replace(K key, V value) {
        MapEntry<K, V> entry = getMapEntry(key);

        if (entry == null) {
            return null;
        }

        V oldValue = entry.value;
        entry.value = value;

        return oldValue;
    }

    @Override
    public int size() {
        return size;
    }

    public int getCapacity() {
        return buckets.length;
    }

    public MapIterator<K, V> iterator() {
        return new CustomHashMapIterator<>(buckets, size);
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "{}";
        }

        StringBuilder hashMapPrint = new StringBuilder("{");
        int elements = 1;
        int ELEMENTS_PER_LINE = 15;

        for (CustomLinkedList<MapEntry<K,V>> linkedList : buckets) {
            if (linkedList != null) {
                CustomIterator<MapEntry<K, V>> iterator = linkedList.iterator();

                while (iterator.hasNext()) {
                    MapEntry<K, V> entry = iterator.next();
                    String appendEntry = entry.key.toString() + "=" + entry.value.toString();
                    hashMapPrint.append(appendEntry).append(", ");

                    if (elements == ELEMENTS_PER_LINE) {
                        hashMapPrint.append("\n");
                        elements = 0;
                    }
                    elements++;
                }
            }
        }

        // Copy hashMapPrint but the ",_blankSpace"
        hashMapPrint = new StringBuilder(hashMapPrint.substring(0, hashMapPrint.length() -2) + "}");

        return hashMapPrint.toString();
    }
    // {Address=Kolkata West-Bengal, Name=Aman Singh}
}
