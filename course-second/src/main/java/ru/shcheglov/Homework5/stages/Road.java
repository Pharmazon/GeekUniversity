/*******************************************************************************
 *  Java Core 2.
 *  Homework 5. Multithreading 2. Race
 *
 *  @author Alexey Shcheglov
 *  @link https://github.com/Pharmazon
 *  @version dated Mar 07, 2018
 ******************************************************************************/

package ru.shcheglov.Homework5.stages;

import ru.shcheglov.Homework5.entity.Car;

public class Road extends Stage {

    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Car car) {
        try {
            System.out.println(car.getName() + " начал этап: " + description);
            Thread.sleep(length / car.getSpeed() * 1000);
            System.out.println(car.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
