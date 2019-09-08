package com.td.game.map;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public class Route implements Serializable {
    private int startX, startY;
    private Vector2[] directions;

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public Vector2[] getDirections() {
        return directions;
    }

    public Route(String str) {
        String[] data = str.split(",");
        startX = Integer.parseInt(data[0]);
        startY = Integer.parseInt(data[1]);
        directions = new Vector2[data[2].length()];
        for (int i = 0; i < data[2].length(); i++) {
            if (data[2].charAt(i) == 'L') {
                directions[i] = new Vector2(-1, 0);
            }
            if (data[2].charAt(i) == 'R') {
                directions[i] = new Vector2(1, 0);
            }
            if (data[2].charAt(i) == 'U') {
                directions[i] = new Vector2(0, 1);
            }
            if (data[2].charAt(i) == 'D') {
                directions[i] = new Vector2(0, -1);
            }
        }
    }
}
