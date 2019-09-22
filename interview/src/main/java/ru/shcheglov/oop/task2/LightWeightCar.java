package ru.shcheglov.oop.task2;

public class LightWeightCar extends Car {

    @Override
    public void move() {
        System.out.println("LightWeightCar is moving");
    }

    @Override
    public void open() {
        System.out.println("LightWeightCar is opened");
    }

    @Override
    public void stop() {
        System.out.println("LightWeightCar is stopped");
    }

    @Override
    public void start() {
        System.out.println("LightWeightCar is starting");
    }
}
