package com.jwt.example.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Component
public class CompletableFutureUtility {

    public void log(String value) {
        System.out.println("Main thread name start " + getCurrentName());
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Current thread in completableFuture " + getCurrentName());
            return "Hello";
        }, executor);
        CompletableFuture<String> completable = completableFuture.thenApply(result -> {
            System.out.println("Current thread in completable " + getCurrentName());
            return result + " world";
        });
        completable.thenAccept(result -> {
            System.out.println("Current thread " + getCurrentName() + " result " + value +" "+ result);
        });
        executor.shutdown();
        System.out.println("Main thread name end " + getCurrentName());
    }

    private static String getCurrentName() {
        return Thread.currentThread().getName();
    }

}
