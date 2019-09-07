package ru.shcheglov.Homework7;

import java.awt.*;

public class CatG extends Cat {

    CatG(String name, int appetite) {
        super(name, appetite);
    }

    void paint(Graphics g, int y, int dx, int dy) {
        if (full) {
            g.setColor(Color.green);
            g.fill3DRect(20, y, appetite * dx + 1, dy + 1, true);
        }
        g.setColor(Color.black);
        g.drawRect(20, y, appetite * dx, dy);
    }
}