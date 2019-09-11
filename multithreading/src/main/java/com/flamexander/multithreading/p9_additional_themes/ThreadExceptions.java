package com.flamexander.multithreading.p9_additional_themes;

public class ThreadExceptions {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            int x = 10;
            System.out.println(1);
            x = x / 0;
            System.out.println(2);
        });
        // Thread.setDefaultUncaughtExceptionHandler(...);
        t.setUncaughtExceptionHandler((t1, e) -> {
            e.printStackTrace();
            System.out.println("Catched");
        });
        t.start();
    }
}
