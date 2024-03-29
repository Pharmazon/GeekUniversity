package com.td.game.gui;

import java.io.Serializable;

public class PlayerInfo implements Serializable {
    private int money;
    private int hp;
    private int hpMax;

    public int getMoney() {
        return money;
    }

    public int getHp() {
        return hp;
    }

    public int getHpMax() {
        return hpMax;
    }

    public PlayerInfo(int money, int hpMax) {
        this.money = money;
        this.hpMax = hpMax;
        this.hp = this.hpMax;
    }

    public boolean isGameOver() {
        return hp <= 0;
    }

    public boolean isMoneyEnough(int amount) {
        return money >= amount;
    }

    public void decreaseMoney(int amount) {
        money -= amount;
    }

    public void decreaseHp(int value) {
        hp -= value;
    }

    public void addMoney(int amount) {
        money += amount;
    }
}
