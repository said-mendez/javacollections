package com.custom.collections.linkedlist;

import com.custom.collections.CustomListIterator;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CustomLinkedListTest {
    private CustomLinkedList<Integer> customLinkedList;

    @BeforeEach
    void createNewCustomLinkedList() {
        customLinkedList = new CustomLinkedList<>();
    }

    @Test
    void permitsAddingNullValues() {
        // Given:
        // customLinkedList

        // When:
        customLinkedList.add(null);

        // Then:
        assertNull(customLinkedList.get(0));
    }

    @Test
    void itKeepsTrackOfTheSize() {
        // Given:
        // customLinkedList

        // When:
        customLinkedList.add(666);
        customLinkedList.add(123);
        customLinkedList.add(13);
        customLinkedList.add(444);
        customLinkedList.add(5);

        // Then:
        assertEquals(5, customLinkedList.size());
    }

    @Test
    void itThrowsIndexOutOfBounds() {
        // Given:
        // customLinkedList
        customLinkedList.add(23);

        // When:
        // customLinkedList.remove(23)

        // Then:
        assertThrows(IndexOutOfBoundsException.class, () -> customLinkedList.remove(23));
    }

    @Test
    public void allowsAddingElementsToLinkedList() {
        // Given:
        CustomLinkedList<String> songs = new CustomLinkedList<>();

        // Then:
        assertDoesNotThrow(() -> {
            // When:
            songs.add("Your Song");
            songs.add("One");
        });
    }

    @Nested
    @DisplayName("When new")
    class WhenNew {
        private CustomLinkedList<Integer> customLinkedList;
        @BeforeEach
        void createNewCustomLinkedList() {
            customLinkedList = new CustomLinkedList<>();
        }

        @Test
        @DisplayName("Is empty")
        void isEmpty() {
            // Given:
            // customLinkedList

            // When:
            boolean isEmpty = customLinkedList.isEmpty();

            // Then:
            assertTrue(isEmpty);
        }

        @Test
        @DisplayName("Allows adding value at index 0")
        void allowsAddingValueAtIndexZero() {
            // Given:
            // customLinkedList

            // When:
            customLinkedList.add(0,20);

            // Then:
            assertEquals(20, customLinkedList.get(0));
        }

        @Test
        @DisplayName("Throws IllegalStateException")
        void itThrowsIllegalStateExceptionOnEmptyLinkedList() {
            // Given:
            // customLinkedList

            // When:
            // customLinkedList.set(0, 100)

            // Then:
            assertThrows(IllegalStateException.class, () -> customLinkedList.set(0, 100));
        }
    }

    @Nested
    @DisplayName("When has values")
    class WhenHasValues {
        private CustomLinkedList<String> movies;
        @BeforeEach
        void createNewCustomLinkedList() {
            movies = new CustomLinkedList<>();
            movies.add("Matrix");
            movies.add("Space Jam");
            movies.add("Harry Potter and the Philosopher's Stone");
            movies.add("Top Gun");
            movies.add("The Simpsons Movie");
        }

        @Test
        void itHasSizeOfFive() {
            // Given:
            // movies

            // When:
            int size = movies.size();

            // Then:
            assertEquals(5, size);
        }

        @Test
        void returnsTrueIfContainsTheElement() {
            // Given:
            // movies

            // When:
            boolean containsElement = movies.contains("Top Gun");

            // Then:
            assertTrue(containsElement);
        }

        @Test
        void returnsFalseIfDoesNotContainsTheElement() {
            // Given:
            // movies

            // When:
            boolean containsElement = movies.contains("Top Gun Maverick");

            // Then:
            assertFalse(containsElement);
        }

        @Test
        void getsTheCorrectElementAtGivenIndex(){
            // Given:
            // movies

            // When:
            String movieName = movies.get(movies.size() - 1);

            // Then:
            assertEquals("The Simpsons Movie", movieName);
        }

        @Test
        void throwsIndexOutOfBoundsWhenTryingToGetElement() {
            // Given:
            // movies

            // When
            // movies.get(movies.size())

            // Then:
            assertThrows(IndexOutOfBoundsException.class, () -> movies.get(movies.size()));
        }

        @Test
        void replacesTheValueAtAGivenIndex() {
            // Given:
            // movies

            // When:
            String newMovie = "Star Wars the Revenge of the Sith";
            Object replacedElement = movies.set(0, newMovie);

            // Then:
            assertEquals("Matrix", replacedElement);
            assertEquals(movies.get(0), newMovie);
        }

        @Test
        void removesElementByIndex() {
            // Given:
            // movies

            // When:
            Object removedElement = movies.remove(2);

            // Then:
            assertEquals("Harry Potter and the Philosopher's Stone", removedElement);
            assertEquals(4, movies.size());
        }

        @Test
        void removesElementByObject() {
            // Given:
            // movies

            // When:
            boolean removedElement = movies.remove("Space Jam");

            // Then:
            assertTrue(removedElement);
            assertEquals(4, movies.size());
        }

        @Test
        void returnsFalseWhenTryingToRemoveNonExistingElement() {
            // Given:
            // movies

            // When:
            boolean removedElement = movies.remove("Space Jam a New Legacy");

            // Then:
            assertFalse(removedElement);
            assertEquals(5, movies.size());
        }

        @Test
        void hasIterator() {
            assertDoesNotThrow(() -> {
                CustomListIterator<String> iterator = movies.iterator();
                while(iterator.hasNext()) {
                    String movie = iterator.next();
                    System.out.println(movie);
                }
            });
        }
    }
}
