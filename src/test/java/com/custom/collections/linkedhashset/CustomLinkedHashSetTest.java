package com.custom.collections.linkedhashset;

import com.custom.collections.CustomIterator;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CustomLinkedHashSetTest {
    @Test
    void doesNotAddDuplicates() {
        // Given:
        CustomLinkedHashSet<String> movies = new CustomLinkedHashSet<>();
        String[] moviesArray = new String [] {
                "Matrix", "Space Jam", "Harry Potter and the Philosopher's Stone",
                "Top Gun", "The Simpsons Movie", "Pokemon 2000", "Star Wars: Clone Wars",
                "Star Wars: The Revenge of the Sith", "The Lord of the Rings The Two Towers",
                "Men of Honor", "Interstellar", "Inception", "Shutter Island", "The Wall Street Wolf",
                "The Dark Knight Rises", "Peaceful Warrior", "Black Panther", "Wakanda Forever",
                "Avengers Infinity War", "Alice in Wonderland"
        };

        // When:
        for (String movie : moviesArray) {
            movies.add(movie);
        }

        for (String movie : moviesArray) {
            movies.add(movie);
        }

        // Then:
        assertEquals(moviesArray.length, movies.size());
    }

    @Test
    public void itKeepsTrackOfTheSizeAndIncreasesCapacityWhenNeeded() {
        // Given:
        CustomLinkedHashSet<String> movies = new CustomLinkedHashSet<>();
        String[] moviesArray = new String [] {
                "Matrix", "Space Jam", "Harry Potter and the Philosopher's Stone",
                "Top Gun", "The Simpsons Movie", "Pokemon 2000", "Star Wars: Clone Wars",
                "Star Wars: The Revenge of the Sith", "The Lord of the Rings The Two Towers",
                "Men of Honor", "Interstellar", "Inception", "Shutter Island", "The Wall Street Wolf",
                "The Dark Knight Rises", "Peaceful Warrior", "Black Panther", "Wakanda Forever",
                "Avengers Infinity War", "Alice in Wonderland"
        };

        int initialCapacity = movies.getCapacity();

        // When:
        for (String movie : moviesArray) {
            movies.add(movie);
        }

        int finalCapacity = movies.getCapacity();

        // Then:
        assertEquals(movies.size(), moviesArray.length);
        assertTrue(initialCapacity < finalCapacity);
    }
    @Test
    @DisplayName("Is empty")
    void whenCustomLinkedHashSetIsNewIsEmpty() {
        // Given:
        CustomLinkedHashSet<String> movies = new CustomLinkedHashSet<>();

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
        CustomLinkedHashSet<String> movies = new CustomLinkedHashSet<>(4);
        movies.add("Interstellar");
        movies.add("Inception");
        movies.add("Shutter Island");

        // When:
        int capacity = movies.getCapacity();

        // Then:
        assertEquals(4, capacity);
    }

    @Test
    void itHasSizeOfSix() {
        // Given:
        CustomLinkedHashSet<String> movies = new CustomLinkedHashSet<>();
        movies.add("Men of Honor");
        movies.add("Interstellar");
        movies.add("Inception");
        movies.add("Shutter Island");
        movies.add("The Wall Street Wolf");
        movies.add("The Last Airbender");

        // When:
        int size = movies.size();

        // Then:
        assertEquals(6, size);
    }

    @Test
    void returnsTrueIfContainsElement() {
        // Given:
        CustomLinkedHashSet<String> movies = new CustomLinkedHashSet<>();
        String[] moviesArray = new String [] {
                "Matrix", "Space Jam", "Harry Potter and the Philosopher's Stone",
                "Top Gun", "The Simpsons Movie", "Pokemon 2000", "Star Wars: Clone Wars",
                "Star Wars: The Revenge of the Sith", "The Lord of the Rings The Two Towers",
                "Men of Honor", "Interstellar", "Inception", "Shutter Island", "The Wall Street Wolf",
                "The Dark Knight Rises", "Peaceful Warrior", "Black Panther", "Wakanda Forever",
                "Avengers Infinity War", "Alice in Wonderland"
        };

        for (String movie : moviesArray) {
            movies.add(movie);
        }
        int index = 0;

        // When: Then:
        CustomIterator<String> iterator = movies.iterator();
        while(iterator.hasNext()) {
            String element = iterator.next();
            assertEquals(moviesArray[index++], element);
        }
    }

    @Test
    void permitsAddingNullValues() {
        // Given:
        CustomLinkedHashSet<String> movies = new CustomLinkedHashSet<>();

        // When:
        movies.add(null);

        // Then:
        assertTrue(movies.contains(null));
    }

    @Test
    void returnsFalseIfDoesNotContainsTheElement() {
        // Given:
        CustomLinkedHashSet<String> movies = new CustomLinkedHashSet<>();
        String[] moviesArray = new String [] {
                "Matrix", "Space Jam", "Harry Potter and the Philosopher's Stone",
                "Top Gun", "The Simpsons Movie", "Pokemon 2000", "Star Wars: Clone Wars",
                "Star Wars: The Revenge of the Sith", "The Lord of the Rings The Two Towers",
                "Men of Honor", "Interstellar", "Inception", "Shutter Island", "The Wall Street Wolf",
                "The Dark Knight Rises", "Peaceful Warrior", "Black Panther", "Wakanda Forever",
                "Avengers Infinity War", "Alice in Wonderland"
        };

        for (String movie : moviesArray) {
            movies.add(movie);
        }

        // When:
        boolean elementIsContained = movies.contains("Top Gun: Maverick");

        // Then:
        assertFalse(elementIsContained);
    }

    @Test
    void removeEmptyHashSetThrowsIllegalStateException() {
        // Given:
        CustomLinkedHashSet<String> movies = new CustomLinkedHashSet<>();

        // When: Then:
        assertThrows(IllegalStateException.class, () -> movies.remove("404 Movie"));
    }

    @Test
    void clearHashSet() {
        // Given:
        CustomLinkedHashSet<String> movies = new CustomLinkedHashSet<>();
        String[] moviesArray = new String [] {
                "Matrix", "Space Jam", "Harry Potter and the Philosopher's Stone",
                "Top Gun", "The Simpsons Movie", "Pokemon 2000", "Star Wars: Clone Wars",
                "Star Wars: The Revenge of the Sith", "The Lord of the Rings The Two Towers",
                "Men of Honor", "Interstellar", "Inception", "Shutter Island", "The Wall Street Wolf",
                "The Dark Knight Rises", "Peaceful Warrior", "Black Panther", "Wakanda Forever",
                "Avengers Infinity War", "Alice in Wonderland"
        };

        for (String movie : moviesArray) {
            movies.add(movie);
        }

        // When:
        movies.clear();

        // Then:
        assertEquals(0, movies.size());
        assertTrue(movies.isEmpty());
    }

    @Test
    void linkedHashSetIterator() {
        // Given:
        CustomLinkedHashSet<String> movies = new CustomLinkedHashSet<>();
        String[] moviesArray = new String [] {
                "Matrix", "Space Jam", "Harry Potter and the Philosopher's Stone",
                "Top Gun", "The Simpsons Movie", "Pokemon 2000", "Star Wars: Clone Wars",
                "Star Wars: The Revenge of the Sith", "The Lord of the Rings The Two Towers",
                "Men of Honor", "Interstellar", "Inception", "Shutter Island", "The Wall Street Wolf",
                "The Dark Knight Rises", "Peaceful Warrior", "Black Panther", "Wakanda Forever",
                "Avengers Infinity War", "Alice in Wonderland"
        };

        for (String movie : moviesArray) {
            movies.add(movie);
        }

        // When: Then:
        assertDoesNotThrow(() -> {
            CustomIterator<String> iterator = movies.iterator();
            while(iterator.hasNext()) {
                iterator.next();
            }
        });
    }

    @Test
    void removeElements(){
        // Given:
        CustomLinkedHashSet<String> movies = new CustomLinkedHashSet<>();
        String[] moviesArray = new String [] {
                "Matrix", "Space Jam", "Harry Potter and the Philosopher's Stone",
                "Top Gun", "The Simpsons Movie", "Pokemon 2000", "Star Wars: Clone Wars",
                "Star Wars: The Revenge of the Sith", "The Lord of the Rings The Two Towers",
                "Men of Honor", "Interstellar", "Inception", "Shutter Island", "The Wall Street Wolf",
                "The Dark Knight Rises", "Peaceful Warrior", "Black Panther", "Wakanda Forever",
                "Avengers Infinity War", "Alice in Wonderland"
        };

        for (String movie : moviesArray) {
            movies.add(movie);
        }

        // When: Then:
        CustomIterator<String> iterator = movies.iterator();
        while(iterator.hasNext()) {
            String element = iterator.next();
            assertTrue(movies.remove(element));
        }

        assertEquals(0, movies.size());
        assertTrue(movies.isEmpty());
    }

    @Test
    void printEmptyLinkedHashSet() {
        // Given
        CustomLinkedHashSet<String> movies = new CustomLinkedHashSet<>();

        // When: Then:
        assertDoesNotThrow(() -> System.out.println(movies));
    }

    @Test
    void iteratingLinkedHashSetKeepElementsInOrder() {
        // Given:
        CustomLinkedHashSet<String> movies = new CustomLinkedHashSet<>();
        String[] moviesArray = new String [] {
                "Matrix", "Space Jam", "Harry Potter and the Philosopher's Stone",
                "Top Gun", "The Simpsons Movie", "Pokemon 2000", "Star Wars: Clone Wars",
                "Star Wars: The Revenge of the Sith", "The Lord of the Rings The Two Towers",
                "Men of Honor", "Interstellar", "Inception", "Shutter Island", "The Wall Street Wolf",
                "The Dark Knight Rises", "Peaceful Warrior", "Black Panther", "Wakanda Forever",
                "Avengers Infinity War", "Alice in Wonderland"
        };

        // When:
        for (String movie : moviesArray) {
            movies.add(movie);
        }

        // Then:
        CustomIterator<String> iterator = movies.iterator();
        int index = 0;

        while(iterator.hasNext()) {
            Object str = iterator.next();
            assertEquals(str, moviesArray[index++]);
        }
    }
}
