package com.jwt.example.javafeatures;

public class StringInternExample {
    public static void main(String[] args) {
        String s1 = new String("hello");
        String s2 = "hello";

        System.out.println(s1 == s2);              // false (different references)
        System.out.println(s1.intern() == s2);     // true (both refer to the pooled string)
    }
}
