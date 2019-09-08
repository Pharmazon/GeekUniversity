package ru.shcheglov.Homework1.fruits;

public class MainClass {

    public static void main(String[] args) {
        Box<Apple> box1 = new Box<>("Box_1");
        Box<Orange> box2 = new Box<>("Box_2");
        Box<Apple> box3 = new Box<>("Box_3");

        box1.addFruits(new Apple(), 1);
        box1.addFruits(new Apple(), 5);
        box1.addFruits(new Apple(),1);
        box1.addFruits(new Apple(), 8);
        box1.addFruits(new Apple(), 12);

        box2.addFruits(new Orange(),1);
        box2.addFruits(new Orange(),5);
        box2.addFruits(new Orange(),6);
        box2.addFruits(new Orange(),4);
        box2.addFruits(new Orange(),2);

        System.out.println(box1);
        System.out.println(box2);
        System.out.println(box3);
        System.out.println("CompareByWeight(int)=" + box1.compareTo(box2));
        System.out.println("CompareByWeight(bool)=" + box1.compare(box2));

        box1.moveFruitsTo(box3);
        System.out.println(box1);
        System.out.println(box2);
        System.out.println(box3);

    }
}
