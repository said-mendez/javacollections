package com.custom.collections.hashmap;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;

public class CustomHashMapTest {
    @Test
    void initialTests() {
        CustomHashMap<Integer, String> customHashMap = new CustomHashMap<>();
        customHashMap.put(null, "Null");
        customHashMap.put(1, "Uno");
        customHashMap.put(2, "Dos");

        System.out.println(customHashMap.size());
        System.out.println(customHashMap.containsKey(null));
        System.out.println(customHashMap.get(null));
        System.out.println(customHashMap.containsValue("Null"));

        customHashMap.put(null, "Null-Edited");
        System.out.println(customHashMap.get(null));
        System.out.println(customHashMap.replace(2, "Two"));
        System.out.println("Removed: " + customHashMap.remove(1));

    }
}
