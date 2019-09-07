package ru.shcheglov.Homework7;

/**
 * Java 1. Homework 7. 
 * Class: MainClass
 *
 * @author Alexey Shcheglov
 * @version dated Dec 16, 2017
 * @link https://github.com/Pharmazon
 */
 
class Main {
    
    public static void main(String[] args) {
        Plate plate = new Plate(80, 100);
        Cat[] cats = {new Cat("Keks", 10), new Cat("Barsik", 20), 
                new Cat("Vasya", 15), new Cat("Pushik", 25), new Cat("Snezhok", 35)};
        System.out.println(plate);

        for (Cat cat : cats) {
            cat.eat(plate);
            System.out.println(cat);
        }

        System.out.println(plate);
        plate.addFood(40);
        System.out.println(plate);

        for (Cat cat : cats) {
            cat.setFull(false);
            cat.eat(plate);
            System.out.println(cat);
        }
        System.out.println(plate);
    }
}


