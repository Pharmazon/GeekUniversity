/*******************************************************************************
 * Java Core 2.
 * Homework 4. Multithreading 1
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version dated Mar 05, 2018
 ******************************************************************************/

package ru.shcheglov.Homework4.firsttask;

public class Monitor {

    private boolean a = false;
    private boolean b = true;
    private boolean c = true;

    public synchronized void printA() {
        try {
            while (a) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("A");
        a = true;
        b = false;
        notify();
    }

    public synchronized void printB() {
        try {
            while (b) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("B");
        b = true;
        c = false;
        notify();
    }

    public synchronized void printC() {
        try {
            while (c) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("C");
        c = true;
        a = false;
        notifyAll();
    }
}
