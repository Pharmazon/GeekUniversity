/*******************************************************************************
 *  Java Core 2.
 *  Homework 5. Multithreading 2. Race
 *
 *  @author Alexey Shcheglov
 *  @link https://github.com/Pharmazon
 *  @version dated Mar 07, 2018
 ******************************************************************************/

package ru.shcheglov.Homework5.entity;

import ru.shcheglov.Homework5.messages.RaceMessages;
import ru.shcheglov.Homework5.stages.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Race {

    private ArrayList<Stage> stages;

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public static void findAndPrintWinner(Car[] cars) {
        boolean isWinnerFound = false;
        while (!isWinnerFound) {
            for (Car car : cars) {
                if (car.isFinished()) {
                    System.out.println(car.getName() + " - WIN!");
                    isWinnerFound = true;
                    break;
                }
            }
        }
    }

    public static void waitOnFinish(ExecutorService executor) {
        executor.shutdown();
        try {
            while (!executor.awaitTermination(24L, TimeUnit.HOURS)) {
                System.out.println(RaceMessages.RACE_IN_PROGRESS);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }
}
