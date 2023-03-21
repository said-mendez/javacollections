package com.custom.collections.hashset;

import com.custom.collections.CustomIterator;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CustomHashSetTest {
    @Test
    public void itKeepsTrackOfTheSizeAndIncreasesCapacityWhenNeeded() {
        // Given:
        CustomHashSet<String> movies = new CustomHashSet<>();

        int initialCapacity = movies.getCapacity();

        // When:
        movies.add("Matrix");
        movies.add("Space Jam");
        movies.add("Harry Potter and the Philosopher's Stone");
        movies.add("Top Gun");
        movies.add("The Simpsons Movie");
        movies.add("Pokemon 2000");
        movies.add("Star Wars: Clone Wars");
        movies.add("Star Wars: The Revenge of the Sith");
        movies.add("The Lord of the Rings The Two Towers");
        movies.add("Men of Honor");
        movies.add("Interstellar");
        movies.add("Inception");
        movies.add("Shutter Island");
        movies.add("The Wall Street Wolf");
        movies.add("The Dark Knight Rises");
        movies.add("Peaceful Warrior");
        movies.add("Matrix");
        movies.add("Space Jam");
        movies.add("Harry Potter and the Philosopher's Stone");
        movies.add("Top Gun");
        movies.add("The Simpsons Movie");
        movies.add("Pokemon 2000");
        movies.add("Star Wars: Clone Wars");
        movies.add("Star Wars: The Revenge of the Sith");
        movies.add("The Lord of the Rings The Two Towers");
        movies.add("Men of Honor");
        movies.add("Interstellar");
        movies.add("Inception");
        movies.add("Shutter Island");
        movies.add("The Wall Street Wolf");
        movies.add("The Dark Knight Rises");
        movies.add("Peaceful Warrior");

        int finalCapacity = movies.getCapacity();

        // Then:
        assertEquals(movies.size(), 16);
        assertTrue(initialCapacity < finalCapacity);
    }
    @Test
    @DisplayName("Is empty")
    void whenCustomHashSetIsNewIsEmpty() {
        // Given:
        CustomHashSet<String> movies = new CustomHashSet<>();

        // When:
        boolean isEmpty = movies.isEmpty();
        int size = movies.size();

        // Then:
        assertTrue(isEmpty);
        assertEquals(0, size);
    }

    @Test
    void createHashSetWithInitialCapacity() {
        // Given:
        CustomHashSet<String> movies = new CustomHashSet<>(2);
        movies.add("Interstellar");
        movies.add("Inception");
        movies.add("Shutter Island");

        // When:
        int capacity = movies.getCapacity();

        // Then:
        assertEquals(2, capacity);
    }

    @Test
    void itHasSizeOfFive() {
        // Given:
        CustomHashSet<String> movies = new CustomHashSet<>();
        movies.add("Men of Honor");
        movies.add("Interstellar");
        movies.add("Inception");
        movies.add("Shutter Island");
        movies.add("The Wall Street Wolf");

        // When:
        int size = movies.size();

        // Then:
        assertEquals(5, size);
    }

    @Test
    void returnsTrueIfContainsLastElement() {
        // Given:
        CustomHashSet<String> movies = new CustomHashSet<>();
        movies.add("Men of Honor");
        movies.add("Interstellar");
        movies.add("Inception");
        movies.add("Shutter Island");
        movies.add("The Wall Street Wolf");

        // When:
        boolean elementIsContained = movies.contains("The Wall Street Wolf");

        // Then:
        assertTrue(elementIsContained);
    }

    @Test
    void permitsAddingNullValues() {
        // Given:
        CustomHashSet<String> movies = new CustomHashSet<>();

        // When:
        movies.add(null);

        // Then:
        assertTrue(movies.contains(null));
    }

    @Test
    void returnsTrueIfContainsMiddleElement() {
        // Given:
        CustomHashSet<String> movies = new CustomHashSet<>();
        movies.add("Men of Honor");
        movies.add("Interstellar");
        movies.add("Inception");
        movies.add("Shutter Island");
        movies.add("The Wall Street Wolf");

        // When:
        boolean elementIsContained = movies.contains("Inception");

        // Then:
        assertTrue(elementIsContained);
    }

    @Test
    void returnsTrueIfContainsElementAtStart() {
        // Given:
        CustomHashSet<String> movies = new CustomHashSet<>();
        movies.add("Men of Honor");
        movies.add("Interstellar");
        movies.add("Inception");
        movies.add("Shutter Island");
        movies.add("The Wall Street Wolf");

        // When:
        boolean elementIsContained = movies.contains("Men of Honor");

        // Then:
        assertTrue(elementIsContained);
    }

    @Test
    void returnsFalseIfDoesNotContainsTheElement() {
        // Given:
        CustomHashSet<String> movies = new CustomHashSet<>();
        movies.add("Men of Honor");
        movies.add("Interstellar");
        movies.add("Inception");
        movies.add("Shutter Island");
        movies.add("The Wall Street Wolf");

        // When:
        boolean elementIsContained = movies.contains("Top Gun");

        // Then:
        assertFalse(elementIsContained);
    }

    @Test
    void removeEmptyHashSetThrowsIllegalStateException() {
        // Given:
        CustomHashSet<String> movies = new CustomHashSet<>();

        // When: Then:
        assertThrows(IllegalStateException.class, () -> movies.remove("404 Movie"));
    }

    @Test
    void clearHashSet() {
        // Given:
        CustomHashSet<String> movies = new CustomHashSet<>();
        movies.add("Matrix");
        movies.add("Space Jam");
        movies.add("Harry Potter and the Philosopher's Stone");
        movies.add("Top Gun");
        movies.add("The Simpsons Movie");
        movies.add("Pokemon 2000");
        movies.add("Star Wars: Clone Wars");
        movies.add("Star Wars: The Revenge of the Sith");
        movies.add("The Lord of the Rings The Two Towers");
        movies.add("Men of Honor");
        movies.add("Interstellar");
        movies.add("Inception");
        movies.add("Shutter Island");
        movies.add("The Wall Street Wolf");
        movies.add("The Dark Knight Rises");
        movies.add("Peaceful Warrior");

        // When:
        movies.clear();

        // Then:
        assertEquals(0, movies.size());
        assertTrue(movies.isEmpty());
    }

    @Test
    void hashSetIterator() {
        // Given:
        CustomHashSet<String> movies = new CustomHashSet<>();
        movies.add("Matrix");
        movies.add("Space Jam");
        movies.add("Harry Potter and the Philosopher's Stone");
        movies.add("Top Gun");
        movies.add("The Simpsons Movie");
        movies.add("Pokemon 2000");
        movies.add("Star Wars: Clone Wars");
        movies.add("Star Wars: The Revenge of the Sith");
        movies.add("The Lord of the Rings The Two Towers");
        movies.add("Men of Honor");
        movies.add("Interstellar");
        movies.add("Inception");
        movies.add("Shutter Island");
        movies.add("The Wall Street Wolf");
        movies.add("The Dark Knight Rises");
        movies.add("Peaceful Warrior");


        // When: Then:
        assertDoesNotThrow(() -> {
            CustomIterator<String> iterator = movies.iterator();
            while(iterator.hasNext()) {
                String movie = iterator.next();
                System.out.println(movie);
            }
        });
    }

    @Test
    void removeElements(){
        // Given:
        CustomHashSet<String> movies = new CustomHashSet<>();
        movies.add("Matrix");
        movies.add("Space Jam");
        movies.add("Harry Potter and the Philosopher's Stone");
        movies.add("Top Gun");
        movies.add("The Simpsons Movie");
        movies.add("Pokemon 2000");
        movies.add("Star Wars: Clone Wars");
        movies.add("Star Wars: The Revenge of the Sith");
        movies.add("The Lord of the Rings The Two Towers");
        movies.add("Men of Honor");
        movies.add("Interstellar");
        movies.add("Inception");
        movies.add("Shutter Island");
        movies.add("The Wall Street Wolf");
        movies.add("The Dark Knight Rises");
        movies.add("Peaceful Warrior");

        // When:
        boolean removedElement = movies.remove("The Lord of the Rings The Two Towers");

        // Then:
        assertTrue(removedElement);
        assertFalse(movies.contains("The Lord of the Rings The Two Towers"));
        assertEquals(15, movies.size());
    }

    @Test
    void printHashSet() {
        // Given:
        CustomHashSet<String> movies = new CustomHashSet<>();
        movies.add("Matrix");
        movies.add("Space Jam");
        movies.add("Harry Potter and the Philosopher's Stone");
        movies.add("Top Gun");
        movies.add("The Simpsons Movie");
        movies.add("Pokemon 2000");
        movies.add("Men of Honor");
        movies.add("Interstellar");
        movies.add("Inception");
        movies.add("Shutter Island");
        movies.add("The Wall Street Wolf");
        movies.add("The Dark Knight Rises");
        movies.add("Peaceful Warrior");
        movies.add("Star Wars: Clone Wars");
        movies.add("Star Wars: The Revenge of the Sith");
        movies.add("The Lord of the Rings The Two Towers");

        // When: Then:
        System.out.println(movies);
    }

    @Test
    void printEmptyHashSet() {
        // Given
        CustomHashSet<String> movies = new CustomHashSet<>();

        // When: Then:
        assertDoesNotThrow(() -> System.out.println(movies));
    }
}
