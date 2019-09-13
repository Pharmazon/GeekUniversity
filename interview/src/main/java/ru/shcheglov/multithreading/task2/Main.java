package ru.shcheglov.multithreading.task2;

public class Main {

    public static void main(String[] args) {

        Counter counter = new Counter();
        for (int i = 0; i < 20; i++) {
            new CounterThread(counter, 1000).start();
        }
    }
}
