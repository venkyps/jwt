package com.jwt.example.javafeatures;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListExample {
    public static void main(String[] args) {
        List<String> cowList = new CopyOnWriteArrayList<>();

        cowList.add("Alice");
        cowList.add("Bob");
        cowList.add("Charlie");

        // Thread-safe iteration, even while modifying
        for (String name : cowList) {
            System.out.println(name);
            if ("Bob".equals(name)) {
                cowList.remove(name);  // No ConcurrentModificationException
            }
        }

        System.out.println("After removal: " + cowList);
    }
}
