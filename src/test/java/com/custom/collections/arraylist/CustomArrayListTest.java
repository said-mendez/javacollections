package com.custom.collections.arraylist;

import com.custom.collections.CustomListIterator;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class CustomArrayListTest {
    private CustomArrayList<Integer> customArrayList;
    @BeforeEach
    void createNewCustomArrayList() {
        customArrayList = new CustomArrayList<>();
    }

    @Test
    void permitsAddingNullValues() {
        // Given:
        // customArrayList

        // When:
        customArrayList.add(null);

        // Then:
        assertNull(customArrayList.get(0));
    }

    @Test
    void itKeepsTrackOfTheSize() {
        // Given
        customArrayList.add(666);
        customArrayList.add(13);
        customArrayList.add(123);
        customArrayList.add(444);

        // When:
        customArrayList.add(5);

        // Then:
        assertEquals(customArrayList.size(), 5);
    }

    @Test
    void itThrowsIndexOutOfBounds() {
        // Given
        // customArrayList

        // When:
        customArrayList.add(23);

        // Then:
        assertThrows(IndexOutOfBoundsException.class, () -> customArrayList.remove(23));
    }

    @Test
    public void allowsAddingElementsToArrayListWithoutInitialCapacity() {
        // Given:
        CustomArrayList<String> songs = new CustomArrayList<>();

        // When: Then:
        assertDoesNotThrow(() -> {
            songs.add("Your Song");
            songs.add("One");
        });
    }

    @Test
    public void allowsAddingMoreElementsThanInitialCapacity() {
        // Given:
        CustomArrayList<Integer> numbers = new CustomArrayList<>(5);

        // Then:
        assertDoesNotThrow(() -> {

            numbers.add(10);
            numbers.add(9);
            numbers.add(8);
            numbers.add(7);
            numbers.add(5);

            // When:
            numbers.add(6);
            numbers.add(4);
            numbers.add(3);
            numbers.add(2);
        });

        // Then:
        assertEquals(9, numbers.size());
    }

    @Test
    public void itReturnsTrueIfElementWasAdded() {
        // Given:
        CustomArrayList<Integer> numbers = new CustomArrayList<>(5);

        // When:
        boolean wasElementAdded =  numbers.add(10);

        // Then:
        assertTrue(wasElementAdded);
    }

    @Nested
    @DisplayName("when array list is new")
    class WhenNew {
        private CustomArrayList<Integer> customArrayList;
        @BeforeEach
        void createNewCustomArrayList() {
            customArrayList = new CustomArrayList<>();
        }

        @Test
        @DisplayName("Is empty")
        void isEmpty() {
            // Given:
            // customArrayList

            // When:
            boolean isEmpty = customArrayList.isEmpty();

            // Then:
            assertTrue(isEmpty);
        }

        @Test
        @DisplayName("Allows adding value at index 0")
        void allowsAddingValueAtIndexZero() {
            // Given:
            // customArrayList

            // When:
            customArrayList.add(0,20);

            // Then:
            assertEquals(20, customArrayList.get(0));
        }

        @Test
        @DisplayName("Allows adding value at index 0")
        void addingValues() {
            // Given:
            customArrayList.add(10);
            customArrayList.add(22);
            customArrayList.add(17);

            // When:
            customArrayList.add(3,20);

            // Then:
            assertEquals(20, customArrayList.get(3));
        }

        @Test
        @DisplayName("Throws IllegalStateException")
        void setThrowsIllegalStateExceptionOnEmptyArrayList() {
            // Given:
            // customArrayList

            // When:
            // customArrayList.set(0, 100)

            // Then:
            assertThrows(IllegalStateException.class, () -> customArrayList.set(0, 100));
        }

        @Test
        void itThrowsIndexOutOfBoundsWhenAddingElementAtIndex() {
            // Given:
            // customArrayList

            // When:
            // customArrayList.add(0, 23)

            // Then:
            assertThrows(IndexOutOfBoundsException.class, () -> customArrayList.add(10, 23));
        }

        @Test
        void indexOfThrowsIllegalStateException() {
            // Given:
            // customArrayList

            // When:
            // customArrayList.indexOf(0)

            // Then:
            assertThrows(IllegalStateException.class, () -> customArrayList.indexOf(0));
        }

        @Test
        void removeThrowsIllegalStateException() {
            // Given:
            // customArrayList

            // When:
            // customArrayList.remove(1)

            // Then:
            assertThrows(IllegalStateException.class, () -> customArrayList.remove(1));
        }

        @Test
        void removeElementThrowsIllegalStateException() {
            // Given:
            // customArrayList

            // When:
            // customArrayList.remove(1)

            // Then:
            assertThrows(IllegalStateException.class, () -> customArrayList.remove((Integer) 10));
        }

        @Test
        void toStringThrowsIllegalStateException() {
            // Given:
            // customArrayList

            // When:
            // System.out.println(customArrayList);

            // Then:
            assertThrows(IllegalStateException.class, () -> System.out.println(customArrayList));
        }
    }

    @Nested
    @DisplayName("When has values")
    class WhenHasValues {
        private CustomArrayList<String> movies;
        @BeforeEach
        void createNewCustomArrayList() {
            movies = new CustomArrayList<>();
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
            boolean elementIsInArrayList = movies.contains("Top Gun");

            // Then:
            assertTrue(elementIsInArrayList);
        }

        @Test
        void returnsFalseIfDoesNotContainsTheElement() {
            // Given:
            // movies

            // When:
            boolean elementIsInArrayList = movies.contains("Top Gun Maverick");

            // Then:
            assertFalse(elementIsInArrayList);
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

            // When:
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
            assertEquals(newMovie, movies.get(0));
        }

        @Test
        void removesElementByIndex() {
            // Given:
            // movies

            // When:
            Object removedElement = movies.remove(2);

            // Then
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
                CustomListIterator<String> iterator = movies.iterator();

                // When:
                while(iterator.hasNext()) {
                    String movie = iterator.next();
                    System.out.println(movie);
                }
            });
        }

        @Test
        void itClearsTheArrayList() {
            // Given:
            // movies

            // When:
            movies.clear();

            // Then:
            assertEquals(0, movies.size());
        }

        @Test
        void itPrintsTheListInArrayFormat() {
            // Given:
            // movies

            // When:
            System.out.println(movies);
        }

        @Test
        void setThrowsIndexOutOfBounds() {
            // Given:
            // movies

            // When:


            // Then:
            assertThrows(IndexOutOfBoundsException.class, () -> movies.set(100, "The Last Dance"));
        }
    }
}
