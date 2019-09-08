/*******************************************************************************
 * Java Core 2.
 * Homework 4. Multithreading 1
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version dated Mar 05, 2018
 ******************************************************************************/

package ru.shcheglov.Homework4.thirdtask;

public class PrintThread extends Thread {

    private MFU mfu;

    private int pages;

    public PrintThread(String name, MFU mfu, int pages) {
        this.setName(name);
        this.mfu = mfu;
        this.pages = pages;
        this.start();
    }

    @Override
    public void run() {
        mfu.print(pages);
    }
}
