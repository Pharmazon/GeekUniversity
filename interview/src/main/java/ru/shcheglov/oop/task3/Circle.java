package ru.shcheglov.homework1.task3;

public class Circle implements Shape {

    private double r;

    public Circle(double r) {
        this.r = r;
    }

    public double calculateArea() {
        return Math.PI * Math.pow(r, 2);
    }
}
