package ru.shcheglov.HW1.competitors;

public abstract class Animal implements Competitor {
    String type;
    String name;
    int maxRunDistance;
    int maxJumpDistance;
    int maxSwimDistance;
    boolean onDistance;

    public Animal(String type, String name, int maxRunDistance, int maxJumpDistance,
                  int maxSwimDistance) {
        this.type = type;
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpDistance = maxJumpDistance;
        this.maxSwimDistance = maxSwimDistance;
        this.onDistance = true;
    }

    public boolean isOnDistance() {
        return onDistance;
    }

    public void run(int distance) {
        if (distance <= maxRunDistance) {
            System.out.println(type + " " + name + " " + "успешно справился с кроссом.");
        } else {
            System.out.println(type + " " + name + " " + "не смог преодолеть кросс.");
            onDistance = false;
        }
    }

    public void jump(int height) {
        if (height <= maxJumpDistance) {
            System.out.println(type + " " + name + " " + "успешно перепрыгнул препятствие.");
        } else {
            System.out.println(type + " " + name + " " + "не смог перепрыгнуть препятствие.");
            onDistance = false;
        }
    }

    public void swim(int distance) {
        if (maxSwimDistance == 0) {
            System.out.println(type + " " + name + " " + "не умеет плавать.");
            onDistance = false;
            return;
        }

        if (distance <= maxSwimDistance) {
            System.out.println(type + " " + name + " " + "успешно проплыл дистанцию.");
        } else {
            System.out.println(type + " " + name + " " + "не смог проплыть дистанцию.");
            onDistance = false;
        }
    }

    public void showResult() {
        System.out.println(type + " " + name + ": " + onDistance);
    }
}
