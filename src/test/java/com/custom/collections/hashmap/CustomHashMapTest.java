package com.custom.collections.hashmap;


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CustomHashMapTest {
    @Test
    void doesNotAddDuplicatesAndKeepsTrackOfTheSize() {
        // Given:
        CustomHashMap<Integer, Integer> numbers = new CustomHashMap<>();
        for (int i = 0; i <= 100; i++) {
            numbers.put(i, i);
        }

        // When:
        for (int i = 0; i <= 100; i++) {
            numbers.put(i, i);
        }


        // Then:
        assertEquals(101, numbers.size());
    }

    @Test
    void increasesCapacityWhenNeeded() {
        // Given:
        CustomHashMap<Integer, Integer> numbers = new CustomHashMap<>();
        int initialCapacity = numbers.getCapacity();

        for (int i = 0; i <= 100; i++) {
            numbers.put(i, i);
        }

        // When:
        int finalCapacity = numbers.getCapacity();


        // Then:
        assertTrue(initialCapacity < finalCapacity);
    }

    @Test
    @DisplayName("Is empty")
    void whenCustomHashMapIsNewIsEmptyAndSizeZero() {
        // Given:
        CustomHashMap<Integer, Integer> numbers = new CustomHashMap<>();

        assertTrue(numbers.isEmpty());
        assertEquals(0, numbers.size());
    }

    @Test
    void createHashMapWithInitialCapacity() {
        // Given:
        CustomHashMap<Integer, String> numbers = new CustomHashMap<>(4);
        numbers.put(1, "Uno");
        numbers.put(2, "Dos");
        numbers.put(3, "Tres");
        numbers.put(4, "Cuatro");

        // When:
        int capacity = numbers.getCapacity();

        // Then:
        assertEquals(4, capacity);
    }

    @Test
    void returnsTrueIfContainsKey() {
        // Given:
        CustomHashMap<Integer, Integer> numbers = new CustomHashMap<>();


        for (int i = 0; i <= 100; i++) {
            numbers.put(i, i);
        }

        // When: Then:
        for (int i = 0; i <= 100; i++) {
            assertTrue(numbers.containsKey(i));
        }
    }

    @Test
    void returnsTrueIfContainsValue() {
        // Given:
        CustomHashMap<Integer, Integer> numbers = new CustomHashMap<>();

        for (int i = 0; i <= 100; i++) {
            numbers.put(i, i);
        }

        // When: Then:
        for (int i = 0; i <= 100; i++) {
            assertTrue(numbers.containsValue(i));
        }
    }

    @Test
    void permitsAddingNullKey() {
        // Given:
        CustomHashMap<Integer, Integer> numbers = new CustomHashMap<>();
        for (int i = 0; i <= 100; i++) {
            numbers.put(i, i);
        }

        // When:
        numbers.put(null, 0);

        // Then:
        assertEquals(0, numbers.get(null));
    }

    @Test
    void puttingSameKeyOverwritesValue() {
        // Given:
        CustomHashMap<Integer, Integer> numbers = new CustomHashMap<>();
        numbers.put(null, 0);

        // When:
        numbers.put(null, 2);

        // Then:
        assertEquals(2, numbers.get(null));
    }

    @Test
    void returnsFalseIfDoesNotContainsKey() {
        // Given:
        CustomHashMap<Integer, Integer> numbers = new CustomHashMap<>();
        for (int i = 0; i <= 100; i++) {
            numbers.put(i, i);
        }

        // When:
        boolean keyWasFound = numbers.containsKey(101);

        // Then:
        assertFalse(keyWasFound);
    }

    @Test
    void returnsFalseIfDoesNotContainsValue() {
        // Given:
        CustomHashMap<Integer, Integer> numbers = new CustomHashMap<>();
        for (int i = 0; i <= 100; i++) {
            numbers.put(i, i);
        }

        // When:
        boolean valueWasFound = numbers.containsValue(101);

        // Then:
        assertFalse(valueWasFound);
    }

    @Test
    void clearHashMap() {
        // Given:
        CustomHashMap<Integer, Integer> numbers = new CustomHashMap<>();
        for (int i = 0; i <= 100; i++) {
            numbers.put(i, i);
        }

        // When:
        numbers.clear();

        // Then:
        assertEquals(0, numbers.size());
        assertTrue(numbers.isEmpty());
    }

    @Test
    void removeElementsByKey(){
        // Given:
        CustomHashMap<Integer, Integer> numbers = new CustomHashMap<>();
        for (int i = 0; i <= 100; i++) {
            numbers.put(i, i);
        }

        // When:
        for (int i = 0; i <= 100; i++) {
            assertEquals(i, numbers.remove(i));
        }

        // Then:
        assertEquals(0, numbers.size());
        assertTrue(numbers.isEmpty());
    }

    @Test
    void removeElementsByKeyValue(){
        // Given:
        CustomHashMap<Integer, Integer> numbers = new CustomHashMap<>();
        for (int i = 0; i <= 100; i++) {
            numbers.put(i, i);
        }

        // When:
        for (int i = 0; i <= 100; i++) {
            assertTrue(numbers.remove(i, i));
        }

        // Then:
        assertEquals(0, numbers.size());
        assertTrue(numbers.isEmpty());
    }

    @Test
    void returnNullWhenRemoveByKeyDoNotExist(){
        // Given:
        CustomHashMap<Integer, Integer> numbers = new CustomHashMap<>();
        for (int i = 0; i <= 100; i++) {
            numbers.put(i, i);
        }

        // When:
        Integer removedValue = numbers.remove(200);

        // Then:
        assertNull(removedValue);
    }

    @Test
    void returnFalseWhenRemoveByKeyValueDoNotExist(){
        // Given:
        CustomHashMap<Integer, Integer> numbers = new CustomHashMap<>();
        for (int i = 0; i <= 100; i++) {
            numbers.put(i, i);
        }

        // When:
        boolean removedValue = numbers.remove(100, 101);

        // Then:
        assertFalse(removedValue);
    }

    @Test
    void replaceAndReturnsReplacedValue(){
        // Given:
        CustomHashMap<Integer, Integer> numbers = new CustomHashMap<>();
        for (int i = 0; i <= 100; i++) {
            numbers.put(i, i);
        }

        // When:
        Integer removedValue = numbers.replace(100, 1000);

        // Then:
        assertEquals(100, removedValue);
    }

    @Test
    void replaceReturnsNullIfKeyNotExist(){
        // Given:
        CustomHashMap<Integer, Integer> numbers = new CustomHashMap<>();
        for (int i = 0; i <= 100; i++) {
            numbers.put(i, i);
        }

        // When:
        Integer removedValue = numbers.replace(1000, 566);

        // Then:
        assertNull(removedValue);
    }

    @Test
    void iteratingHashMap() {
        // Given:
        CustomHashMap<Integer, Integer> numbers = new CustomHashMap<>();
        for (int i = 0; i <= 200; i++) {
            numbers.put(i, i);
        }
        int numberOfElements = 0;

        // When:
        MapIterator<Integer, Integer> iterator = numbers.iterator();
        while(iterator.hasNext()) {
            MapEntry<Integer, Integer> entry = iterator.next();
            assertEquals(entry.value, entry.key);
            numberOfElements++;
        }

        // Then:
        assertEquals(numberOfElements, numbers.size());
    }

    @Test
    void printCustomHashMapDoNotThrowsException() {
        // Given:
        CustomHashMap<Integer, Integer> numbers = new CustomHashMap<>();

        for (int i = 0; i <= 200; i++) {
            numbers.put(i, i);
        }

        // When: Then:
        assertDoesNotThrow(() -> System.out.println(numbers));
    }

    @Test
    void printEmptyCustomHashMapDoNotThrowsException() {
        // Given:
        CustomHashMap<Integer, Integer> numbers = new CustomHashMap<>();

        // When: Then:
        assertDoesNotThrow(() -> System.out.println(numbers));
    }

    @Test
    void computeChangesTheValueAccordingly() {
        // Given:
        CustomHashMap<Integer, Integer> numbers = new CustomHashMap<>();

        for (int i = 0; i <= 100; i++) {
            numbers.put(i, i);
        }

        // When:
        int oldValue = numbers.compute(100, (key, value) -> value * 10);

        // Then:
        assertEquals(100, oldValue);
        assertEquals(oldValue * 10, numbers.get(100));
    }

    @Test
    void computeNotChangesTheValueIfNewValueIsNull() {
        // Given:
        CustomHashMap<Integer, Integer> numbers = new CustomHashMap<>();

        for (int i = 0; i <= 100; i++) {
            numbers.put(i, i);
        }

        // When:
        int oldValue = numbers.compute(0, (key, value) -> null);

        // Then:
        assertFalse(numbers.containsKey(0));
    }

    @Test
    void computeWithNull() {
        // Given:
        CustomHashMap<Integer, Integer> numbers = new CustomHashMap<>();

        for (int i = 0; i <= 100; i++) {
            numbers.put(i, i);
        }

        // When:
        numbers.replace(0, null);

        // Then:
        assertNull(numbers.compute(0, (key, value) -> null));
        assertNull(numbers.compute(0, (key, value) -> 0));
        assertEquals(0, numbers.get(0));
    }
}
