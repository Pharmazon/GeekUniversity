package ru.shcheglov.Homework1.fruits;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> implements Comparable<Box> {

    private List<T> arrayList;
    private String boxname;

    public Box(String name) {
        arrayList = new ArrayList<>();
        this.boxname = name;
    }

    public String getBoxname() {
        return boxname;
    }

    public float getWeight() {
        if (arrayList.isEmpty()) return 0;
        return arrayList.size() * arrayList.get(0).weight;
    }

    public String getType() {
        if (arrayList.isEmpty()) return "empty";
        return arrayList.get(0).getClass().getSimpleName();
    }

    public void addFruits(T fruit, int quantity) {
        if (quantity <= 0) return;
        for (int i = 0; i < quantity; i++) {
            arrayList.add(fruit);
        }
    }

    public void moveFruitsTo(Box<T> box) {
        if (arrayList.isEmpty()) return;
        for (T fruit : arrayList) {
            box.addFruits(fruit, 1);
        }
        System.out.println("Fruits " + this.getType() + "s moved from " + this.getBoxname() + " to "
                + box.getBoxname() + ".");
        arrayList.clear();
    }

    public int getFruitsCount() {
        if (arrayList.isEmpty()) return 0;
        return arrayList.size();
    }

    @Override
    public int compareTo(Box o) {
        return (int) (this.getWeight() - o.getWeight());
    }

    public boolean compare(Box o) {
        if (this.getWeight() == o.getWeight()) return true;
        else return false;
    }

    @Override
    public String toString() {
        return this.getBoxname() + " [" +
                "fruits=" + this.getType() + "; " +
                "count=" + this.getFruitsCount() + "; " +
                "weight=" + this.getWeight() +
                "]";
    }
}
