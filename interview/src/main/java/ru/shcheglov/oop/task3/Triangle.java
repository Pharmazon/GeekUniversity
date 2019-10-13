package ru.shcheglov.homework1.task3;

public class Triangle implements Shape {

    private double a;

    private double h;

    public Triangle(double a, double h) {
        this.a = a;
        this.h = h;
    }

    public double calculateArea() {
        return (1 * a * h) / 2 ;
    }
}
