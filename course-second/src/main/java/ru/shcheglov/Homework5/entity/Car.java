/*******************************************************************************
 *  Java Core 2.
 *  Homework 5. Multithreading 2. Race
 *
 *  @author Alexey Shcheglov
 *  @link https://github.com/Pharmazon
 *  @version dated Mar 07, 2018
 ******************************************************************************/

package ru.shcheglov.Homework5.entity;

import ru.shcheglov.Homework5.Application;
import ru.shcheglov.Homework5.messages.RaceMessages;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public final class Car implements Runnable {

    private final static CyclicBarrier startLine =
            new CyclicBarrier(Application.CARS_COUNT, new RaceMessages());
    private static int CARS_COUNT = 0;
    private final Race race;

    private final int speed;
    private final String name;
    private boolean isFinished;

    public Car(final Race race, final int speed) {
        this.race = race;
        this.speed = speed;
        this.isFinished = false;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    public int getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }

    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            startLine.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        System.out.println(this.name + " закончил гонку.");
        isFinished = true;
    }
}
