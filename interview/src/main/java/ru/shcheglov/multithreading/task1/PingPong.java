package ru.shcheglov.multithreading.task1;

public class PingPong {

    private static boolean isPlaying = true;
    private static final Object BALL = new Object();
    private static String lastAction = "ping";

    public static void main(String[] args) {
        new Thread(() -> {
            while (isPlaying) {
                try {
                    Thread.sleep(1000);
                    synchronized (BALL) {
                        if (!lastAction.equals("ping")) {
                            System.out.printf("%s: %s \n", Thread.currentThread().getName(), "ping");
                            lastAction = "ping";
                        }
                    }
                } catch (InterruptedException e) {
                    isPlaying = false;
                }
            }
        }).start();

        new Thread(() -> {
            while (isPlaying) {
                try {
                    Thread.sleep(3000);
                    synchronized (BALL) {
                        if (!lastAction.equals("pong")) {
                            System.out.printf("%s: %s \n", Thread.currentThread().getName(), "pong");
                            lastAction = "pong";
                        }
                    }
                } catch (InterruptedException e) {
                    isPlaying = false;
                }
            }
        }).start();
    }
}
