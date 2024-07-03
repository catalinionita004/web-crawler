package com.example.webcrawler.utils;

public class ThreadSleeper {
    public static void sleep(float seconds){
        try {
            Thread.sleep((long) (1000 * seconds));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
