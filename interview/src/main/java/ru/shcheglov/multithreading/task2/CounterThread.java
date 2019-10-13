package ru.shcheglov.multithreading.task2;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CounterThread extends Thread {

    @NonNull
    private Counter counter;

    @NonNull
    private int delay;

    private boolean isRunning = true;

    @Override
    public void run() {
        while (isRunning) {
            try {
                counter.getLock().lock();
                counter.increment();
                System.out.printf("%s: %s \n", Thread.currentThread().getName(), counter.getValue());
                sleep(delay);
            } catch (InterruptedException e) {
                isRunning = false;
            } finally {
                counter.getLock().unlock();
            }
        }
    }

    public void stopCount() {
        isRunning = false;
    }
}
