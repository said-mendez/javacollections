package com.custom.collections.linkedlist;

import com.custom.collections.CustomIterator;
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

    @Test
    public void removeElementWhenSizeIsOne() {
        // Given:
        CustomLinkedList<String> songs = new CustomLinkedList<>();
        songs.add("The Unforgiven");

        // When:
        boolean elementWasRemoved = songs.remove("The Unforgiven");

        // Then:
        assertTrue(elementWasRemoved);

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

        @Test
        void addThrowsIndexOutOfBounds() {
            // Given
            // customLinkedList

            // When:
            // customLinkedList.add(10, 100)

            // Then:
            assertThrows(IndexOutOfBoundsException.class, () -> customLinkedList.add(10, 100));
        }

        @Test
        void addElementAtGivenIndex() {
            // Given:
            // customLinkedList
            customLinkedList.add(100);
            customLinkedList.add(200);
            customLinkedList.add(300);
            customLinkedList.add(400);

            // When:
            customLinkedList.add(2, 1234);

            // Then:
            assertEquals(1234, customLinkedList.get(2));
        }

        @Test
        void getThrowsIllegalStateException() {
            // Given:
            // customLinkedList

            // When:
            // customLinkedList.get(0)

            assertThrows(IllegalStateException.class, () -> customLinkedList.get(0));
        }

        @Test
        void indexOfThrowsIllegalStateException() {
            // Given:
            // customLinkedList

            // When:
            // customLinkedList.indexOf("The Greatest Showman")

            assertThrows(IllegalStateException.class, () -> customLinkedList.indexOf(7));
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
            boolean containsElement = movies.contains("Matrix");

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
            // Then:
            assertDoesNotThrow(() -> {
                // Given:
                CustomIterator<String> iterator = movies.iterator();

                // When:
                while(iterator.hasNext()) {
                    String movie = iterator.next();
                    System.out.println(movie);
                }
            });
        }

        @Test
        void indexOfReturnsIndexOfElement() {
            // Given:
            // movies

            // When:
            int indexOf = movies.indexOf("Matrix");

            assertEquals(0, indexOf);
        }

        @Test
        void indexOfReturnsMinusOneWhenElementDoesNotExists() {
            // Given:
            // movies

            // When:
            int indexOf = movies.indexOf("Top Gun Maverick");

            assertEquals(-1, indexOf);
        }

        @Test
        void removeInBetweenElement() {
            // Given:
            // movies

            // When:
            boolean elementWasRemoved = movies.remove("Top Gun");

            // Then:
            assertTrue(elementWasRemoved);
        }

        @Test
        void removeLastIndex() {
            // Given:
            // movies

            // When:
            String removedElement = movies.remove(4);

            // Then:
            assertEquals("The Simpsons Movie", removedElement);
        }

        @Test
        void getMiddle() {
            // Given:
            // movies

            // When:
            String movieName = movies.get(2);

            // Then:
            assertEquals("Harry Potter and the Philosopher's Stone", movieName);
        }

        @Test
        void setAtIndexOutOfBounds() {
            // Given:
            // movies

            // When:
            // movies.set(5, "404 Movie")

            // Then:
            assertThrows(IndexOutOfBoundsException.class, () -> movies.set(5, "404 Movie"));
        }

        @Test
        void clearLinkedList() {
            // Given:
            // movies

            // When:
            movies.clear();

            // Then:
            assertEquals(0, movies.size());
            assertTrue(movies.isEmpty());
        }

        @Test
        void removingAtIndexZero() {
            // Given:
            // movies

            // When:
            String removedElement = movies.remove(0);

            // Then:
            assertEquals("Matrix", removedElement);
        }

        @Test
        void removingAtHead() {
            // Given:
            // movies

            // When:
            boolean elementWasRemoved = movies.remove("Matrix");

            // Then:
            assertTrue(elementWasRemoved);
        }

        @Test
        void removingAtEnd() {
            // Given:
            // movies

            // When:
            boolean elementWasRemoved = movies.remove("The Simpsons Movie");

            // Then:
            assertTrue(elementWasRemoved);
        }

        @Test
        void tryingToRemoveNonExistingElement() {
            // Given:
            // movies

            // When:
            boolean elementWasRemoved = movies.remove("Movie 404!");

            // Then:
            assertFalse(elementWasRemoved);
        }

        @Test
        void printLinkedList() {
            // Given:
            // movies

            // Then:
            assertDoesNotThrow(() -> {
                // When:
                System.out.println(movies);
            });
        }

        @Test
        void printEmptyLinkedList() {
            // Given:
            CustomLinkedList<String> linkedList = new CustomLinkedList<>();

            // Then:
            assertDoesNotThrow(() -> {
                // When:
                System.out.println(linkedList);
            });
        }
    }
}
