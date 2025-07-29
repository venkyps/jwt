package com.jwt.example.javafeatures;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class ConcurrentHashMapExample {
    public static void main(String[] args) {
        Map<String, Integer> userAges = new ConcurrentHashMap<>();

        // Put some data
        userAges.put("Alice", 30);
        userAges.put("Bob", 25);
        userAges.put("Charlie", 35);

        // Safe concurrent read and write
        userAges.forEach((name, age) -> {
            System.out.println(name + ": " + age);
            if (age < 30) {
                userAges.remove(name);  // Safe to remove
            }
        });

        System.out.println("After removal: " + userAges);
    }
}
