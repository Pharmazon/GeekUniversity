/*******************************************************************************
 *  Java Core 2.
 *  Homework 5. Multithreading 2. Race
 *
 *  @author Alexey Shcheglov
 *  @link https://github.com/Pharmazon
 *  @version dated Mar 07, 2018
 ******************************************************************************/

package ru.shcheglov.Homework5.stages;

import ru.shcheglov.Homework5.Application;
import ru.shcheglov.Homework5.entity.Car;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    private Semaphore tunnelPass =
            new Semaphore(Application.CARS_COUNT / 2, true);

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
            tunnelPass.acquire();
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(c.getName() + " закончил этап: " + description);
            tunnelPass.release();
        }
    }
}
