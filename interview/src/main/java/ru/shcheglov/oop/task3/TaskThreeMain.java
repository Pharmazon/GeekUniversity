package ru.shcheglov.homework1.task3;

public class TaskThreeMain {

    public static void main(String[] args) {
        Shape circle = new Circle(3);
        Shape triangle = new Triangle(10, 5);
        Shape square = new Square(5, 4);

        double circleArea = circle.calculateArea();
        double triangleArea = triangle.calculateArea();
        double squareArea = square.calculateArea();

        System.out.println(circleArea);
        System.out.println(triangleArea);
        System.out.println(squareArea);
    }
}
