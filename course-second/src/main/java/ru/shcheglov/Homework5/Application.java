/*******************************************************************************
 *  Java Core 2.
 *  Homework 5. Multithreading 2. Race
 *
 *  @author Alexey Shcheglov
 *  @link https://github.com/Pharmazon
 *  @version dated Mar 07, 2018
 ******************************************************************************/

package ru.shcheglov.Homework5;

import ru.shcheglov.Homework5.entity.Car;
import ru.shcheglov.Homework5.entity.Race;
import ru.shcheglov.Homework5.messages.RaceMessages;
import ru.shcheglov.Homework5.stages.Road;
import ru.shcheglov.Homework5.stages.Tunnel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        System.out.println(RaceMessages.RACE_PREPARATION);

        final Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        final ExecutorService executor = Executors.newFixedThreadPool(CARS_COUNT);
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
            executor.submit(cars[i]);
        }

        //looking for winner
        Race.findAndPrintWinner(cars);

        //main thread should wait for all cars finish the race
        Race.waitOnFinish(executor);

        System.out.println(RaceMessages.RACE_FINISHED);
    }
}
