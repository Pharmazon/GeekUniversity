package ru.shcheglov.HW1.competitors;

public interface Competitor {
    void run(int distance);
    void swim(int distance);
    void jump(int distance);
    boolean isOnDistance();
    void showResult();
}
