package ru.shcheglov.HW1.competitors;

public class Team {

    public String teamname;
    public Human[] teammates;

    public Team(String teamname, Human[] teammates) {
        this.teamname = teamname;
        this.teammates = teammates;
    }

    public void printTeammatesComplDistance() {
        for (Human h : teammates) {
            if (h.isOnDistance()) {
                System.out.println("Команда " + this.teamname + ": " +
                        h.name + " успешно прошел полосу препятствий.");
            } else {
                System.out.println("Команда " + this.teamname + ": " +
                        h.name + " сошел с дистанции.");
            }
        }
    }

    public void printAllTeammates() {
        for (Human h : teammates) {
            System.out.println("Команда " + this.teamname + ": " +
                    h.name + " может плавать на " + h.maxSwimDistance +
                    " м., прыгать на " + h.maxJumpDistance + " м., бегать на " +
                    h.maxRunDistance + "м., прошел дистанцию: " + h.isOnDistance());
        }
    }
}
