package com.td.game.monster;

import java.io.Serializable;

public class MonsterTemplate implements Serializable {
    private int monsterType;
    private float speed;
    private int hpMax;
    private int damagePower;
    private int killPremium;

    public int getMonsterType() {
        return monsterType;
    }

    public float getSpeed() {
        return speed;
    }

    public int getHpMax() {
        return hpMax;
    }

    public int getDamagePower() {
        return damagePower;
    }

    public int getKillPremium() {
        return killPremium;
    }

    public MonsterTemplate(String line) {
        String[] tokens = line.split("\\s");
        this.monsterType = Integer.parseInt(tokens[0]);
        this.speed = Float.parseFloat(tokens[1]);
        this.hpMax = Integer.parseInt(tokens[2]);
        this.damagePower = Integer.parseInt(tokens[3]);
        this.killPremium = Integer.parseInt(tokens[4]);
    }
}
