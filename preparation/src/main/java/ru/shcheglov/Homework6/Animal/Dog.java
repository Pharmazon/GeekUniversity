package ru.shcheglov.Homework6.Animal;

public class Dog extends Animal {

    Dog(String name) {
        super(name);
        this.limrun = 500;
        this.limswim = 10;
        this.limjump = 0.5;
    }
}