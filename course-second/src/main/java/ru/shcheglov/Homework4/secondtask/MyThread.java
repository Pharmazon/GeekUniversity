/*******************************************************************************
 * Java Core 2.
 * Homework 4. Multithreading 1
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version dated Mar 05, 2018
 ******************************************************************************/

package ru.shcheglov.Homework4.secondtask;

public class MyThread extends Thread {

    public MyThread(String name) {
        this.setName(name);
        this.start();
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                File.writeToFile(Thread.currentThread().getName() + ": added entry " + i + "/10.");
                Thread.sleep(20);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        File.writeToFile(Thread.currentThread().getName() + ": thread finished.");
        System.out.println(Thread.currentThread().getName() + ": thread finished.");
    }
}
