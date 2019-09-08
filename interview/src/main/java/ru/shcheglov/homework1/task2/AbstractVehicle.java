package ru.shcheglov.homework1.task2;

public abstract class AbstractVehicle implements Vehicle {

    private String color;
    private String name;
    private Engine engine;
    private int speed;

    @Override
    public void move() {
        System.out.println("Vehicle is moving");
    }

    @Override
    public void open() {
        System.out.println("Vehicle is opened");
    }

    @Override
    public void stop() {
        System.out.println("Vehicle is stopped");
    }

    @Override
    public void start(){
        System.out.println("Vehicle is starting");
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
