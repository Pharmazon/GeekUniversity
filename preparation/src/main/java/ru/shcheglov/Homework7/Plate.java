package ru.shcheglov.Homework7;

/**
 * Java 1. Homework 7.
 * Class: Plate
 *
 * @author Alexey Shcheglov
 * @version dated Dec 16, 2017
 * @link https://github.com/Pharmazon
 */
 
class Plate {
    protected int food;
    protected int limit;
    
    public static void main(String[] args){
    }
    
    Plate(int food, int limit) {
        this.food = food;
        this.limit = limit;
    }
    
    void setFood(int food) {
        if (food > 0 && food <= limit) this.food = food;
    }
    
    boolean decreaseFood(int portion) {
        if (food < portion) return false;
        food -= portion;
        return true;
    }
    
    void addFood(int food) {
        if (this.food + food <= limit)
            this.food += food;
        System.out.println("The food added to the plate: " + food);
    }
    
    @Override
    public String toString() {
        return "The food left in the plate: " + food;
    }
}