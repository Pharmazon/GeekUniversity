package com.td.game.monster;

import java.io.Serializable;

public class Wave implements Serializable {
    private float time;
    private int routeIndex;
    private int monstersCount;
    private int monsterType;

    public float getTime() {
        return time;
    }

    public int getRouteIndex() {
        return routeIndex;
    }

    public int getMonstersCount() {
        return monstersCount;
    }

    public int getMonsterType() {
        return monsterType;
    }

    public Wave(String line) {
        String[] tokens = line.split("\\s");
        this.time = Float.parseFloat(tokens[0]);
        this.routeIndex = Integer.parseInt(tokens[1]);
        this.monstersCount = Integer.parseInt(tokens[2]);
        this.monsterType = Integer.parseInt(tokens[3]);
    }
}
