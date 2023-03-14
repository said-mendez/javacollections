package com.custom.collections;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CustomLinkedListTest {
    private CustomLinkedList<Integer> customLinkedList;

    @BeforeEach
    void createNewCustomArrayList() {
        this.customLinkedList = new CustomLinkedList<>();
    }

    @Test
    void permitsAddingNullValues() {
        this.customLinkedList.add(null);
        assertNull(this.customLinkedList.get(0));
    }

    @Test
    void itKeepsTrackOfTheSize() {
        this.customLinkedList.add(666);
        this.customLinkedList.add(123);
        this.customLinkedList.add(13);
        this.customLinkedList.add(444);
        this.customLinkedList.add(5);
        assertEquals(this.customLinkedList.size(), 5);
    }

    @Test
    void itThrowsIndexOutOfBounds() {
        this.customLinkedList.add(23);
        assertThrows(IndexOutOfBoundsException.class, () -> this.customLinkedList.remove(23));
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
        CustomArrayList<Integer> numbers = new CustomArrayList<>(5);
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
    @DisplayName("When new")
    class WhenNew {
        private CustomLinkedList<Integer> customLinkedList;
        @BeforeEach
        void createNewCustomArrayList() {
            this.customLinkedList = new CustomLinkedList<>();
        }

        @Test
        @DisplayName("Is empty")
        void isEmpty() {
            assertTrue(this.customLinkedList.isEmpty());
        }

        @Test
        @DisplayName("Allows adding value at index 0")
        void allowsAddingValueAtIndexZero() {
            this.customLinkedList.add(0,20);
            assertSame(20, this.customLinkedList.get(0));
        }

        @Test
        @DisplayName("Throws IllegalStateException")
        void itThrowsIllegalStateExceptionOnEmptyArrayList() {
            assertThrows(IllegalStateException.class, () -> this.customLinkedList.set(0, 100));
        }
    }

    @Nested
    @DisplayName("When has values")
    class WhenHasValues {
        private CustomLinkedList<String> movies;
        @BeforeEach
        void createNewCustomArrayList() {
            this.movies = new CustomLinkedList<>();
            this.movies.add("Matrix");
            this.movies.add("Space Jam");
            this.movies.add("Harry Potter and the Philosopher's Stone");
            this.movies.add("Top Gun");
            this.movies.add("The Simpsons Movie");
        }

        @Test
        void itHasSizeOfFive() {
            assertEquals(this.movies.size(), 5);
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
            assertEquals(this.movies.get(this.movies.size() - 1), "The Simpsons Movie");
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
            assertEquals(this.movies.get(0), newMovie);
        }

        @Test
        void removesElementByIndex() {
            Object removedElement = this.movies.remove(2);
            assertEquals(removedElement, "Harry Potter and the Philosopher's Stone");
            assertEquals(this.movies.size(), 4);
        }

        @Test
        void removesElementByObject() {
            boolean removedElement = this.movies.remove("Space Jam");
            assertTrue(removedElement);
            assertEquals(this.movies.size(), 4);
        }

        @Test
        void returnsFalseWhenTryingToRemoveNonExistingElement() {
            boolean removedElement = this.movies.remove("Space Jam a New Legacy");
            assertFalse(removedElement);
            assertEquals(this.movies.size(), 5);
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
