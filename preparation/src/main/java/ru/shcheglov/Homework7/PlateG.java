package ru.shcheglov.Homework7;

import java.awt.*;

public class PlateG extends Plate {

    PlateG(int food, int limit) {
        super(food, limit);
    }

    void paint(Graphics g, int windowWidth, int dy) {
        g.setColor(Color.red);
        g.fill3DRect(10, 10, food * (windowWidth - 20) / limit + 1, dy + 1, false);
        g.setColor(Color.black);
        g.drawRect(10, 10, windowWidth - 20, dy);
    }
}
