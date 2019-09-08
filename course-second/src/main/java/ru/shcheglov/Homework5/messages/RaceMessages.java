/*******************************************************************************
 *  Java Core 2.
 *  Homework 5. Multithreading 2. Race
 *
 *  @author Alexey Shcheglov
 *  @link https://github.com/Pharmazon
 *  @version dated Mar 07, 2018
 ******************************************************************************/

package ru.shcheglov.Homework5.messages;

public class RaceMessages implements Runnable {

    public static final String RACE_PREPARATION = "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!";

    public static final String RACE_IN_PROGRESS = "ГОНКА ПРОДОЛЖАЕТСЯ!";

    public static final String RACE_FINISHED = "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!";

    @Override
    public void run() {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
    }
}
