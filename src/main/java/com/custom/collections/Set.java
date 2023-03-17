package com.custom.collections;

public interface Set<E> {
    // Appends the specified element to the end of this set.
    boolean add(E element);
    // Returns true if this set contains the specified element.
    boolean contains(E element);
    // Returns true if this set contains no elements.
    boolean isEmpty();
    // Returns the number of elements in this set.
    int size();
    // Removes all the elements from this set (optional operation).
    void clear();
    // Removes the first occurrence of the specified element from this set, if it is present (optional operation).
    boolean remove(E e);
    // Returns the hash code value for this set.
    int hashCode();
    // Equals
    boolean equals(Object o);
    // set iterator
    CustomListIterator<E> iterator();
}
