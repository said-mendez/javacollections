package com.custom.collections;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class CustomArrayListTest {
    private CustomArrayList<Integer> customArrayList;
    @BeforeEach
    void createNewCustomArrayList() {
        this.customArrayList = new CustomArrayList<Integer>();
    }

    @Test
    void permitsAddingNullValues() {
        this.customArrayList.add(null);
        assertTrue(this.customArrayList.get(0) == null);
    }

    @Test
    void itKeepsTrackOfTheSize() {
        this.customArrayList.add(666);
        this.customArrayList.add(123);
        this.customArrayList.add(13);
        this.customArrayList.add(444);
        this.customArrayList.add(5);
        assertEquals(this.customArrayList.size(), 5);
    }

    @Test
    void itThrowsIndexOutOfBounds() {
        this.customArrayList.add(23);
        assertThrows(IndexOutOfBoundsException.class, () -> this.customArrayList.remove(23));
    }

    @Test
    public void allowsAddingElementsToArrayListWithoutInitialCapacity() {
        CustomArrayList<String> songs = new CustomArrayList<>();
        assertDoesNotThrow(() -> {
            songs.add("Your Song");
            songs.add("One");
        });
    }

    @Test
    public void allowsAddingMoreElementsThanInitialCapacity() {
        CustomArrayList<Integer> numbers = new CustomArrayList<Integer>(5);
        assertDoesNotThrow(() -> {
            numbers.add(10);
            numbers.add(9);
            numbers.add(8);
            numbers.add(7);
            numbers.add(6);
            numbers.add(5);
            numbers.add(4);
            numbers.add(3);
            numbers.add(2);
        });
        assertEquals(9, numbers.size());
    }

    @Nested
    @DisplayName("when new")
    class WhenNew {
        private CustomArrayList<Integer> customArrayList;
        @BeforeEach
        void createNewCustomArrayList() {
            this.customArrayList = new CustomArrayList<Integer>();
        }

        @Test
        @DisplayName("Is empty")
        void isEmpty() {
            assertTrue(this.customArrayList.isEmpty());
        }

        @Test
        @DisplayName("Allows adding value at index 0")
        void allowsAddingValueAtIndexZero() {
            this.customArrayList.add(0,20);
            assertTrue(this.customArrayList.get(0) == (Integer)20);
        }

        @Test
        @DisplayName("Throws IllegalStateException")
        void itThrowsIllegalStateExceptionOnEmptyArrayList() {
            assertThrows(IllegalStateException.class, () -> this.customArrayList.set(0, 100));
        }
    }

    @Nested
    @DisplayName("When has values")
    class WhenHasValues {
        private CustomArrayList movies;
        @BeforeEach
        void createNewCustomArrayList() {
            this.movies = new CustomArrayList<String>();
            this.movies.add("Matrix");
            this.movies.add("Space Jam");
            this.movies.add("Harry Potter and the Philosopher's Stone");
            this.movies.add("Top Gun");
            this.movies.add("The Simpsons Movie");
        }

        @Test
        void itHasSizeOfFive() {
            assertEquals(5, this.movies.size());
        }

        @Test
        void returnsTrueIfContainsTheElement() {
            assertTrue(this.movies.contains("Top Gun"));
        }

        @Test
        void returnsFalseIfDoesNotContainsTheElement() {
            assertFalse(this.movies.contains("Top Gun Maverick"));
        }

        @Test
        void getsTheCorrectElementAtGivenIndex(){
            assertEquals("The Simpsons Movie", this.movies.get(this.movies.size() - 1));
        }

        @Test
        void throwsIndexOutOfBoundsWhenTryingToGetElement() {
            assertThrows(IndexOutOfBoundsException.class, () -> this.movies.get(this.movies.size()));
        }

        @Test
        void replacesTheValueAtAGivenIndex() {
            String newMovie = "Star Wars the Revenge of the Sith";
            Object replacedElement = this.movies.set(0, newMovie);
            assertEquals("Matrix", replacedElement);
            assertEquals(newMovie, this.movies.get(0));
        }

        @Test
        void removesElementByIndex() {
            Object removedElement = this.movies.remove(2);
            assertEquals("Harry Potter and the Philosopher's Stone", removedElement);
            assertEquals(4, this.movies.size());
        }

        @Test
        void removesElementByObject() {
            boolean removedElement = this.movies.remove("Space Jam");
            assertTrue(removedElement);
            assertEquals(4, this.movies.size());
        }

        @Test
        void returnsFalseWhenTryingToRemoveNonExistingElement() {
            boolean removedElement = this.movies.remove("Space Jam a New Legacy");
            assertFalse(removedElement);
            assertEquals(5, this.movies.size());
        }

        @Test
        void hasIterator() {
            assertDoesNotThrow(() -> {
                CustomListIterator<String> iterator = this.movies.iterator();
                while(iterator.hasNext()) {
                    String movie = iterator.next();
                    System.out.println(movie);
                }
            });
        }
    }
}
