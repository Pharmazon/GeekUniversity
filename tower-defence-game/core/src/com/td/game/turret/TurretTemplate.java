package com.td.game.turret;

import java.io.Serializable;

public class TurretTemplate implements Serializable {
    private int turretType;
    private int costConstruction;
    private int damage;
    private int radius;
    private float fireRate;
    private int premiumDestruction;
    private float bulletSpeed;
    private float rotationSpeed;

    public int getTurretType() {
        return turretType;
    }

    public int getCostConstruction() {
        return costConstruction;
    }

    public int getDamage() {
        return damage;
    }

    public int getRadius() {
        return radius;
    }

    public float getFireRate() {
        return fireRate;
    }

    public int getPremiumDestruction() {
        return premiumDestruction;
    }

    public float getBulletSpeed() {
        return bulletSpeed;
    }

    public float getRotationSpeed() {
        return rotationSpeed;
    }

    public TurretTemplate(String line) {
        String[] tokens = line.split("\\s");
        this.turretType = Integer.parseInt(tokens[0]);
        this.costConstruction = Integer.parseInt(tokens[1]);
        this.fireRate = Float.parseFloat(tokens[2]);
        this.damage = Integer.parseInt(tokens[3]);
        this.radius = Integer.parseInt(tokens[4]);
        this.premiumDestruction = Integer.parseInt(tokens[5]);
        this.bulletSpeed = Float.parseFloat(tokens[6]);
        this.rotationSpeed = Float.parseFloat(tokens[7]);
    }
}