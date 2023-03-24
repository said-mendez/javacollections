package com.custom.collections.hashmap;
public interface MapIterator<K, V> {
    boolean hasNext();
    MapEntry<K,V> next();
}
