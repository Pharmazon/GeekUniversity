package ru.shcheglov.homework1.task3;

public class Square implements Shape {

    private double a;

    private double b;

    public Square(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double calculateArea() {
        return a * b;
    }
}
