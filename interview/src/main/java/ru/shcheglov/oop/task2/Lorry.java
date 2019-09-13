package ru.shcheglov.homework1.task2;

class Lorry extends AbstractVehicle {

    @Override
    public void move() {
        System.out.println("Lorry is moving");
    }

    @Override
    public void open() {
        System.out.println("Lorry is opened");
    }

    @Override
    public void stop() {
        System.out.println("Lorry is stopped");
    }

    @Override
    public void start() {
        System.out.println("Lorry is starting");
    }
}
