package ru.shcheglov.HW1;

import ru.shcheglov.HW1.competitors.Human;
import ru.shcheglov.HW1.competitors.Team;
import ru.shcheglov.HW1.obstacles.*;

/**
 * Java Core 1. Homework 1.
 * Class: MainClass
 *
 * @author Alexey Shcheglov
 * @link https://github.com/Pharmazon
 * @version dated Jan 14, 2018
 */

public class MainClass {

    public static void main(String[] args) {
        Obstacle[] obstacles = {new Cross(400), new Wall(4), new Water(10)};
        Course course = new Course(obstacles);
        Human[] humans = {new Human("Алиса", 200, 1, 500),
                new Human("Петя", 5000, 5, 200),
                new Human("Вася", 410, 5, 100),
                new Human("Рита", 450, 4, 11),
                new Human("Иванна", 500, 2, 5), };
        Team team = new Team("'Победители'", humans);
        course.doAllCourse(team);
        System.out.println("====================================");
        team.printTeammatesComplDistance();
    }
}
