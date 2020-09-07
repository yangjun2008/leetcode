package com.m3.learning.java.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println("Executing in " + Thread.currentThread().getName());
        };

        ExecutorService executor = Executors.newSingleThreadExecutor();

        CompletableFuture<Void> cf = CompletableFuture.runAsync(runnable, executor);
        cf.thenRun(()->System.out.println("I'm done"));

    }

}
