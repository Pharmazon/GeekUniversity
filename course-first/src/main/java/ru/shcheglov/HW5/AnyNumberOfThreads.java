package ru.shcheglov.HW5;

import java.util.Arrays;

public class AnyNumberOfThreads {

    static final int SIZEOFARRAY = 10_000_000;
    static final int THREADS_COUNT = 4;
    static final int PART_SIZE = SIZEOFARRAY / THREADS_COUNT;

    public static void main(String[] args) {
        float[] array = new float[SIZEOFARRAY];
        Arrays.fill(array, 1f);
        long timestart = System.currentTimeMillis();
        float[][] m = new float[THREADS_COUNT][PART_SIZE];
        Thread[] t = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            System.arraycopy(array, PART_SIZE * i, m[i],0, PART_SIZE);
            final int u = i;
            t[i] = new Thread(() -> {
                for (int j = 0, z = PART_SIZE * u; j < PART_SIZE; j++) {
                    m[u][j] = (float)(m[u][j] * Math.sin(0.2f + z / 5) * Math.cos(0.2f + z / 5) *
                            Math.cos(0.4f + z / 2));
                }
            });
            t[i].start();
        }
        for (int i = 0; i < THREADS_COUNT; i++) {
            System.arraycopy(m[i], 0, array, i * PART_SIZE, PART_SIZE);
        }
        long timefinish = System.currentTimeMillis();
        System.out.println("Executed in " + (timefinish - timestart) + " ms.");
    }
}
