package com.flamexander.multithreading.p5_executor_service;

import java.util.concurrent.*;

public class ExecutorServiceApp {

    public static void main(String[] args) {
//        ExecutorService serv = Executors.newFixedThreadPool(5);
//        for (int i = 0; i < 12; i++) {
//            String w = "#" + i;
//            serv.execute(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(Thread.currentThread().getName() + " - " + w + "-start");
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(Thread.currentThread().getName() + " - " + w + "-end");
//                }
//            });
//        }
//        serv.shutdown();

        ExecutorService service = Executors.newFixedThreadPool(4);


        Future<String> stringFuture = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                int x = 10 / 0;
                return "Java";
            }
        });
        try {
            String result = stringFuture.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }
}
