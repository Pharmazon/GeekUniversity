package ru.shcheglov.Homework7;

/**
 * Java 1. Homework 7. 
 * Class: Cat
 *
 * @author Alexey Shcheglov
 * @version dated Dec 16, 2017
 * @link https://github.com/Pharmazon
 */

class Cat {
    protected String name;
    protected int appetite;
    protected boolean full;

    public static void main(String[] args) {
    }

    Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.full = false;
    }

    void eat(Plate plate) {
        if (!full) full = plate.decreaseFood(appetite);
    }

    void setFull(boolean bool) {
        this.full = bool;
    }

    @Override
    public String toString() {
        return "{name=" + name + ", appetite=" +
                appetite + ", fullness=" + full + "}";
    }
}