package ru.shcheglov.HW1.obstacles;

import ru.shcheglov.HW1.competitors.Competitor;

public class Water extends Obstacle {

    private int distance;

    public Water(int distance) {
        this.distance = distance;
    }

    @Override
    public void doIt(Competitor competitors) {
        competitors.swim(distance);
    }
}
