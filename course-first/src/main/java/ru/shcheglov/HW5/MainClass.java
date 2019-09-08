package ru.shcheglov.HW5;

/**
 * Java Core 1.
 * Multithreading.
 * Homework 5.
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version dated Feb 01, 2018
 */

import java.util.Arrays;

public class MainClass {
    static final int SIZE = 10_000_000;
    static final int HALF = SIZE / 2;
    static float[] array = new float[SIZE];


    public static void main(String[] args) {
        singleThreading();
        multiThreading();
    }
    public static void singleThreading() {
        Arrays.fill(array, 1f);
        long a = System.currentTimeMillis();
        calculate(array, 0);
        System.out.println("singleThread executed in " + (System.currentTimeMillis() - a) + " ms.");
    }

    public static void multiThreading() {
        float[] firstHalf = new float[HALF];
        float[] secondHalf = new float[HALF];
        Arrays.fill(array, 1f);
        long a = System.currentTimeMillis();
        System.arraycopy(array, 0, firstHalf, 0, HALF);
        System.arraycopy(array, HALF, secondHalf, 0, HALF);

        Thread thread1 = new Thread(() -> calculate(firstHalf, 0));
        Thread thread2 = new Thread(() -> calculate(secondHalf, HALF));
        try {
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(firstHalf, 0, array, 0, HALF);
        System.arraycopy(secondHalf, 0, array, HALF, HALF);

        System.out.println("multiThreading executed in " + (System.currentTimeMillis() - a) + " ms.");
    }

    private static void calculate(float[] array, int offset) {
        for (int i = 0, j = offset + i; i < array.length; i++, j++) {
            array[i] = (float)(array[i] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) *
                    Math.cos(0.4f + j / 2));
        }
    }
}
