package ru.shcheglov.HW1.obstacles;

import ru.shcheglov.HW1.competitors.Competitor;

public class Cross extends Obstacle {

    private int distance;

    public Cross(int distance) {
        this.distance = distance;
    }

    @Override
    public void doIt(Competitor competitors) {
        competitors.run(distance);
    }
}
