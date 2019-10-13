package ru.shcheglov.multithreading.task2;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Getter
@NoArgsConstructor
class Counter {

    private int value = 0;
    private Lock lock = new ReentrantLock();

    void increment() {
        value++;
    }
}
