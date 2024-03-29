package com.td.game.turret;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.td.game.map.Map;
import com.td.game.monster.Monster;
import com.td.game.screen.GameScreen;
import com.td.game.util.Assets;
import com.td.game.util.GameAudio;

import java.io.Serializable;

public class Turret implements Serializable {
    private GameScreen gameScreen;
    private Map map;
    private int type;
    private int damage;
    private transient TextureRegion[] regions;
    private transient GameAudio gameAudio;
    private Vector2 position;
    private float angle;
    private float range;
    private float fireDelay;
    private float fireTimer;
    private float rotationSpeed;
    private Monster target;
    private boolean active;
    private Vector2 tmpVector;
    private int costConstruction;
    private int premiumDestruction;
    private float bulletSpeed;
    private StringBuilder sbHUD;

    public boolean isActive() {
        return active;
    }

    public int getCellX() {
        return (int) (position.x / 80);
    }

    public int getCellY() {
        return (int) (position.y / 80);
    }

    public int getType() {
        return type;
    }

    public void setRegions(TextureRegion[] regions) {
        this.regions = regions;
    }

    public void setGameAudio(GameAudio gameAudio) {
        this.gameAudio = gameAudio;
    }

    public Turret(TextureRegion[] regions, GameScreen gameScreen, Map map, float cellX, float cellY) {
        this.regions = regions;
        this.gameScreen = gameScreen;
        this.map = map;
        this.range = 0.0f;
        this.costConstruction = 0;
        this.premiumDestruction = 0;
        this.damage = 0;
        this.bulletSpeed = 0.0f;
        this.rotationSpeed = 0.0f;
        this.fireDelay = 0.0f;
        this.position = new Vector2(cellX * 80 + 40, cellY * 80 + 40);
        this.angle = 0.0f;
        this.tmpVector = new Vector2(0, 0);
        this.gameAudio = Assets.getInstance().getGameAudio();
        this.sbHUD = new StringBuilder(10);
        this.active = false;
    }
    public void activate(TurretTemplate template, int cellX, int cellY) {
        this.range = template.getRadius();
        this.fireDelay = template.getFireRate();
        this.type = template.getTurretType();
        this.damage = template.getDamage();
        this.costConstruction = template.getCostConstruction();
        this.premiumDestruction = template.getPremiumDestruction();
        this.bulletSpeed = template.getBulletSpeed();
        this.rotationSpeed = template.getRotationSpeed();
        setTurretToCell(cellX, cellY);
        active = true;
    }

    public void deactivate() {
        active = false;
    }

    public void setTurretToCell(int cellX, int cellY) {
        if (map.isGrass(cellX, cellY)) {
            position.set(cellX * 80 + 40, cellY * 80 + 40);
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(regions[type], position.x - 40, position.y - 40, 40, 40, 80, 80, 1, 1, angle);
    }

    public void renderHUD(SpriteBatch batch, BitmapFont font) {
        sbHUD.setLength(0);
        sbHUD.append("lev." + type);
        font.draw(batch, sbHUD, position.x - 30, position.y + 58, 60, 1, false);
    }

    public boolean checkMonsterInRange(Monster monster) {
        return Vector2.dst(position.x, position.y, monster.getPosition().x, monster.getPosition().y) < range;
    }

    public void update(float dt) {
        if (target != null && (!checkMonsterInRange(target) || !target.isAlive())) {
            target = null;
        }
        if (target == null) {
            Monster[] monsters = gameScreen.getMonsterEmitter().getMonsters();
            for (int i = 0; i < monsters.length; i++) {
                if (monsters[i].isAlive() && checkMonsterInRange(monsters[i])) {
                    target = monsters[i];
                    break;
                }
            }
        }
        checkRotation(dt);
        tryToFire(dt);
    }

    public float getAngleToTarget() {
        return tmpVector.set(target.getPosition()).sub(position).angle();
    }

    public void checkRotation(float dt) {
        if (target != null) {
            float angleTo = getAngleToTarget();
            if (angle > angleTo) {
                if (Math.abs(angle - angleTo) <= 180.0f) {
                    angle -= rotationSpeed * dt;
                } else {
                    angle += rotationSpeed * dt;
                }
            }
            if (angle < angleTo) {
                if (Math.abs(angle - angleTo) <= 180.0f) {
                    angle += rotationSpeed * dt;
                } else {
                    angle -= rotationSpeed * dt;
                }
            }
            if (angle < 0.0f) {
                angle += 360.0f;
            }
            if (angle > 360.0f) {
                angle -= 360.0f;
            }
        }
    }

    public void tryToFire(float dt) {
        fireTimer += dt;
        if (target != null && fireTimer >= fireDelay && Math.abs(angle - getAngleToTarget()) < 15) {
            fireTimer = 0.0f;
            float fromX = position.x + (float) Math.cos(Math.toRadians(angle)) * 28;
            float fromY = position.y + (float) Math.sin(Math.toRadians(angle)) * 28;
            float time = Vector2.dst(fromX, fromY, target.getPosition().x, target.getPosition().y) / bulletSpeed;
            float toX = target.getPosition().x + target.getVelocity().x * time;
            float toY = target.getPosition().y + target.getVelocity().y * time;
            gameScreen.getParticleEmitter().setupByTwoPoints(fromX, fromY, toX, toY, time, 1.2f, 1.5f, 1,
                    1, 0, 1, 1, 0, 0, 1);
            gameScreen.getParticleEmitter().setupByTwoPoints(fromX, fromY, toX + MathUtils.random(-10, 10),
                    toY + MathUtils.random(-10, 10), time, 1.2f, 1.5f, 1, 1, 0, 1,
                    1, 0, 0, 1);
            gameAudio.shoot(type);

            if (target.takeDamage(damage)) {
                gameAudio.killMonster();
                gameScreen.getPlayerInfo().addMoney(target.getPremiumKill());
            }
        }
    }
}
