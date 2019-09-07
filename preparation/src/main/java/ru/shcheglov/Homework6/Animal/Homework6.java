package ru.shcheglov.Homework6.Animal;

/**
 * Java 1. Homework 6.
 *
 * @author Alexey Shcheglov
 * @version dated Dec 11, 2017
 * @link https://github.com/Pharmazon
 */
 
class Homework6 {
    
    public static void main(String[] args) {
        
        Cat cat = new Cat("Keks");
        Dog dog = new Dog("Snickers");

        cat.show("Cat");
        cat.run(100);
        cat.run(210);
        cat.jump(1.5);
        cat.jump(2.5);
        cat.swim(30);
        
        dog.show("Dog");
        dog.run(400);
        dog.run(520);
        dog.jump(0.6);
        dog.jump(0.1);
        dog.swim(5);
        dog.swim(15);
    }
}