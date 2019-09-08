package ru.shcheglov.HW1.obstacles;

import ru.shcheglov.HW1.competitors.Human;
import ru.shcheglov.HW1.competitors.Team;

public class Course {

    Obstacle[] course;

    public Course(Obstacle[] course) {
        this.course = course;
    }

    public void doAllCourse(Team team) {
        for (Human h : team.teammates) {
            for (Obstacle o : course) {
                o.doIt(h);
                if (!h.isOnDistance()) break;
            }
        }
    }
}
