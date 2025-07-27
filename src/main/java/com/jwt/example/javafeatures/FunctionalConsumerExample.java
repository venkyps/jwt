package com.jwt.example.javafeatures;

import java.util.function.Consumer;

public class FunctionalConsumerExample {
    public static void main(String[] args) {
        Consumer<String> consumer = (name) -> System.out.println("value of the string ::"+name);
        consumer.accept("dynamic value");
    }
}
