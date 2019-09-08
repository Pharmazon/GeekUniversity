/*******************************************************************************
 * Java Core 2.
 * Homework 4. Multithreading 1
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version dated Mar 05, 2018
 ******************************************************************************/

package ru.shcheglov.Homework4.thirdtask;

public class MFU {

    private final Object printMonitor = new Object();

    private final Object scanMonitor = new Object();

    public void print(int pagenum) {
        synchronized (printMonitor) {
            try {
                for (int i = 1; i <= pagenum; i++) {
                    System.out.println(Thread.currentThread().getName() + ": printing... " + i + "/" + pagenum + " page(s).");
                    Thread.sleep(50);
                }
                System.out.println("[DONE] " + Thread.currentThread().getName() + " printed successfully.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void scan(int pagenum) {
        synchronized (scanMonitor) {
            try {
                for (int i = 1; i <= pagenum; i++) {
                    System.out.println(Thread.currentThread().getName() + ": scanning... " + i + "/" + pagenum + " page(s).");
                    Thread.sleep(50);
                }
                System.out.println("[DONE] " + Thread.currentThread().getName() + " scanned successfully.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
