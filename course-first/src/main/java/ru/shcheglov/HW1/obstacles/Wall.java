package ru.shcheglov.HW1.obstacles;

import ru.shcheglov.HW1.competitors.Competitor;

public class Wall extends Obstacle {

    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public void doIt(Competitor competitors) {
        competitors.jump(height);
    }
}
