package ru.shcheglov.Homework6.Animal;

/**
 * Java 1. Homework 6. Animal super class, Dog and Cat subclasses. 
 *
 * @author Alexey Shcheglov
 * @version dated Dec 11, 2017
 * @link https://github.com/Pharmazon
 */
 
abstract class Animal {
    
    private String name;
    int limrun;
    int limswim;
    double limjump;

    public static void main(String[] args) {  
    }
    
    public Animal(String name) {
        this.name = name;
    }
    
    void run(int dist) {
        boolean res = false;
        if (dist < this.limrun && dist > 0) res = true;
        else res = false;
        System.out.println(" | run " + dist + " m: " + res);
    }    

    void swim(int dist) {
        boolean res = false;
        if (dist < this.limswim && dist > 0) res = true;
        else res = false;
        System.out.println(" | swim " + dist + " m: " + res);
    }
    
    void jump(double height) {
        boolean res = false;
        if (height < this.limjump && height > 0) res = true;
        else res = false;
        System.out.println(" | jump " + height + " m: " + res);
    }    

    String getName() {
        return this.name;
    }

    void setName(String name) {
        this.name = name;
    }    
    
    void show(String animal) {
        System.out.println(animal + ": " + this.getName());
    }      
}





