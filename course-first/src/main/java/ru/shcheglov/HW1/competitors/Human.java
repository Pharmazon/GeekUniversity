package ru.shcheglov.HW1.competitors;

public class Human implements Competitor {

    String name;
    int maxRunDistance;
    int maxJumpDistance;
    int maxSwimDistance;
    boolean onDistance;

    public Human(String name, int maxRunDistance, int maxJumpDistance, int maxSwimDistance) {
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
            System.out.println(name + " " + "успешно справился с кроссом.");
        } else {
            System.out.println(name + " " + "не смог преодолеть кросс.");
            onDistance = false;
        }
    }

    public void jump(int height) {
        if (height <= maxJumpDistance) {
            System.out.println(name + " " + "успешно перепрыгнул препятствие.");
        } else {
            System.out.println(name + " " + "не смог перепрыгнуть препятствие.");
            onDistance = false;
        }
    }

    public void swim(int distance) {
        if (maxSwimDistance == 0) {
            System.out.println(name + " " + "не умеет плавать.");
            onDistance = false;
            return;
        }

        if (distance <= maxSwimDistance) {
            System.out.println(name + " " + "успешно проплыл дистанцию.");
        } else {
            System.out.println(name + " " + "не смог проплыть дистанцию.");
            onDistance = false;
        }
    }

    public void showResult() {
        System.out.println(name + ": " + onDistance);
    }

}
