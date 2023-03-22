package com.custom.collections;

import java.util.function.BiFunction;

public interface CustomMap<K, V> {
    void clear();
    // Attempts to compute a mapping for the specified key and its current mapped value (or null if there is no current mapping).
    V compute(K key, BiFunction<? super K,? super V,? extends V> remappingFunction);

    // Returns true if this map contains a mapping for the specified key.
    boolean containsKey(K key);

    // Returns true if this map maps one or more keys to the specified value.
    boolean containsValue(V value);

    // Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
    V get(K key);

    // Returns true if this map contains no key-value mappings.
    boolean isEmpty();

    // Associates the specified value with the specified key in this map (optional operation).
    V put(K key, V value);

    // Removes the mapping for a key from this map if it is present (optional operation).
    V remove(K key);

    // Removes the entry for the specified key only if it is currently mapped to the specified value.
    boolean remove(K key, V value);

    // Replaces the entry for the specified key only if it is currently mapped to some value.
    // Returns the replaced value.
    V replace(K key, V value);

    // Returns the number of key-value mappings in this map.
    int size();
}
