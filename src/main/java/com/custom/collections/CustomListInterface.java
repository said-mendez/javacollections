package com.custom.collections;

import java.util.Iterator;

/*
    Implementing Iterable interface allows an object to be the target of the "for-each loop" statement.
*/
public interface CustomListInterface<E> {
    // Appends the specified element to the end of this list.
    boolean add(E element);
    // Inserts the specified element at the specified position in this list.
    void add(int index, E element) throws IndexOutOfBoundsException;
    // Returns true if this list contains the specified element.
    boolean contains(Object o);
    // Returns the element at the specified position in this list.
    Object get(int index) throws IndexOutOfBoundsException, IllegalStateException;
    // Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
    int indexOf(Object o) throws IllegalStateException;
    // Returns true if this list contains no elements.
    boolean isEmpty();
    // Returns the number of elements in this list.
    int size();
    // Replaces the element at the specified position in this list with the specified collection.
    E set(int index, E element) throws IllegalStateException, IndexOutOfBoundsException;
    // Removes all the elements from this list (optional operation).
    void clear();
    // Removes the element at the specified position in this list (optional operation).
    Object remove(int index) throws IllegalStateException;
    // Removes the first occurrence of the specified element from this list, if it is present (optional operation).
    boolean remove(Object o);
    // Returns an array containing all the elements in this list in proper sequence (from first to last element).
    Object[] toArray();
}