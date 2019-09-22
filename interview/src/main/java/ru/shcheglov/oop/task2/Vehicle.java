package ru.shcheglov.oop.task2;

public interface Vehicle extends Openable, Stopable, Movable, Startable {

    String getColor();

    void setColor(String color);

    String getName();

    void setName(String name);

    Engine getEngine();

    void setEngine(Engine engine);

    int getSpeed();

    void setSpeed(int speed);
}
