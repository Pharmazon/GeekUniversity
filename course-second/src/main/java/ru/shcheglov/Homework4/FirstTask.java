/*******************************************************************************
 * Java Core 2.
 * Homework 4. Multithreading 1
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version dated Mar 05, 2018
 ******************************************************************************/

package ru.shcheglov.Homework4;

//1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5
//раз, порядок должен быть именно ABСABСABС. Используйте wait/notify/notifyAll.

import ru.shcheglov.Homework4.firsttask.Monitor;

public class FirstTask {

    public static void main(String[] args) {

        final Monitor monitor = new Monitor();

        final int cycles = 10;

        final Thread a = new Thread(() -> {
            for (int i = 1; i <= cycles ; i++) {
                monitor.printA();
            }
        });
        a.start();

        final Thread b = new Thread(() -> {
            for (int i = 1; i <= cycles ; i++) {
                monitor.printB();
            }
        });
        b.start();

        final Thread c = new Thread(() -> {
            for (int i = 1; i <= cycles ; i++) {
                monitor.printC();
            }
        });
        c.start();
    }
}
