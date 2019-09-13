package ru.shcheglov.oop.task2;

public class Car extends AbstractVehicle {

    @Override
    public void move() {
        System.out.println("Car is moving");
    }

    @Override
    public void open() {
        System.out.println("Car is opened");
    }

    @Override
    public void stop() {
        System.out.println("Car is stopped");
    }

    @Override
    public void start() {
        System.out.println("Car is starting");
    }

}
