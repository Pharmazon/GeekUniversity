/*******************************************************************************
 * Java Core 2.
 * Homework 4. Multithreading 1
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version dated Mar 05, 2018
 ******************************************************************************/

package ru.shcheglov.Homework4;

//2. Написать совсем небольшой метод, в котором 3 потока построчно пишут данные
//в файл (штук по 10 записей, с периодом в 20 мс)

import ru.shcheglov.Homework4.secondtask.MyThread;

public class SecondTask {

    public static void main(String[] args) {
        new MyThread("Thread-A");
        new MyThread("Thread-B");
        new MyThread("Thread-C");
    }
}
