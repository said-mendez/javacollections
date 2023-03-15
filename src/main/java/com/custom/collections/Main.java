package com.custom.collections;

import com.custom.collections.linkedlist.CustomLinkedList;

public class Main {
    public static void main(String[] args) {
        CustomListInterface<String> movies = new CustomLinkedList<>();

        movies.add("La La Land");
        movies.add("Matrix");
        movies.add(0,"Men of Honor");
        movies.add("Everything Everywhere All At Once");
        movies.add("Space Jam A New Legacy");
        movies.add(1, "The Lord of The Rings: The Return of the King");
        movies.add(6, "Warrior");
        movies.add(7, "Pinocchio");
        System.out.println(movies);
        System.out.println("Size: " + movies.size());
        System.out.println("get 7: " + movies.get(1));
        System.out.println(movies.indexOf("Warrior"));
        System.out.println(movies.contains("The Lord of The Rings: The Return of the King"));
        System.out.println(movies.set(4, "The Office"));
        System.out.println("Size: " + movies.size());
        System.out.println(movies.remove(7));
        System.out.println("Size: " + movies.size());
        movies.set(1, "Pokemon");
        System.out.println(movies);

        // movies.clear();
        System.out.println("Movies Size: " + movies.size());
        System.out.println(movies.isEmpty());
        movies.add("First Movie");
        System.out.println(movies);

        CustomListIterator<String> iterator = movies.iterator();
        while(iterator.hasNext()) {
            String movie = iterator.next();
            System.out.println(movie);
        }


//        CustomArrayList<Integer> numbers = new CustomArrayList<>(3);
//
//        numbers.add(0, 1);
//        numbers.add(2);
//        numbers.add(3);
//        numbers.add(3, 666);
//        System.out.println(numbers.get(0));
//        //numbers.add("abc");
//         System.out.println(numbers.get(3));
//        // System.out.println(numbers.size());
//        numbers.add(0, 23);
//        System.out.println(numbers.get(4));
//        System.out.println("IndexOf: " + numbers.indexOf(666));
//        System.out.println(numbers.get(0));
//        System.out.println(numbers.size());
//        System.out.println(numbers);
//        numbers.remove((Integer) 666);
//        System.out.println(numbers);
//        System.out.println(numbers.size());
//        System.out.println(numbers.contains(2));

//
//        CustomListIterator<Integer> iterator = numbers.iterator();
//        while(iterator.hasNext()) {
//            int num = iterator.next();
//            System.out.println(num);
//        }

//
//        for (int i = 0; i < numbers.size(); i++) {
//            System.out.println(numbers.get(i));
//        }

//            ArrayList<String> songs = new ArrayList<String>(20);
//            songs.add("La Guitarra");
//            songs.add("Your Song");
//            songs.add("Fade to Black");
//            ArrayList<String> newSongs = new ArrayList<String>() {
//                {
//                    add("Lucy in the Sky with Diamonds");
//                    add("Imagine");
//                }
//            };
//            songs.addAll(newSongs);
//        //songs.add(5, "One");
//        System.out.println(songs);
//        System.out.println(songs.size());
//
//        // create a ArrayList String type
//        // and Initialize an ArrayList with add()
//        ArrayList<String> gfg = new ArrayList<String>() {
//            {
//                add("Geeks");
//                add("for");
//                add("Geeks");
//            }
//        };
    }
}