package com.td.game.monster;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.td.game.map.Map;
import com.td.game.map.Route;
import com.td.game.util.Assets;

import java.io.Serializable;

public class Monster implements Serializable {
    private transient TextureRegion[] regions;
    private transient TextureRegion textureBackHp;
    private transient TextureRegion textureHp;
    private Map map;
    private Vector2 position;
    private Vector2 velocity;
    private float speed;
    private boolean alive;
    private Route route;
    private int routeCounter;
    private int lastCellX, lastCellY;
    private float offsetX, offsetY;
    private int hp;
    private int hpMax;
    private StringBuilder sbHUD;
    private int type;
    private int damagePower;
    private int premiumKill;

    public boolean isAlive() {
        return alive;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public int getCellX() {
        return (int)(position.x / 80);
    }

    public int getCellY() {
        return (int)(position.y / 80);
    }

    public int getDamagePower() {
        return damagePower;
    }

    public int getPremiumKill() {
        return premiumKill;
    }

    public void setRegions(TextureRegion[] regions) {
        this.regions = regions;
    }

    public void setTextureBackHp(TextureRegion textureBackHp) {
        this.textureBackHp = textureBackHp;
    }

    public void setTextureHp(TextureRegion textureHp) {
        this.textureHp = textureHp;
    }

    public Monster(TextureRegion[] regions, Map map, int routeIndex) {
        this.regions = regions;
        this.map = map;
        this.textureBackHp = Assets.getInstance().getAtlas().findRegion("monsterBackHP");
        this.textureHp = Assets.getInstance().getAtlas().findRegion("monsterHp");
        this.speed = 100.0f;
        this.damagePower = 1;
        this.premiumKill = 0;
        this.route = Assets.getInstance().getLevelLoader().getRoutes().get(routeIndex);
        this.offsetX = MathUtils.random(10, 70);
        this.offsetY = MathUtils.random(10, 70);
        this.position = new Vector2(route.getStartX() * 80 + offsetX, route.getStartY() * 80 + offsetY);
        this.lastCellX = route.getStartX();
        this.lastCellY = route.getStartY();
        this.routeCounter = 0;
        this.velocity = new Vector2(route.getDirections()[0].x * speed, route.getDirections()[0].y * speed);
        this.hpMax = 25;
        this.hp = this.hpMax;
        this.alive = false;
        this.sbHUD = new StringBuilder(20);
        this.type = 0;
    }

    public boolean takeDamage(int dmg) {
        hp -= dmg;
        if (hp <= 0) {
            alive = false;
            return true;
        }
        return false;
    }

    public void deactivate() {
        alive = false;
    }

    public void activate(MonsterTemplate template, int routeIndex) {
        this.offsetX = MathUtils.random(10, 70);
        this.offsetY = MathUtils.random(10, 70);
        this.route = Assets.getInstance().getLevelLoader().getRoutes().get(routeIndex);
        this.position.set(route.getStartX() * 80 + offsetX, route.getStartY() * 80 + offsetY);
        this.lastCellX = route.getStartX();
        this.lastCellY = route.getStartY();
        this.routeCounter = 0;
        this.velocity.set(route.getDirections()[0].x * speed, route.getDirections()[0].y * speed);
        this.hpMax = template.getHpMax();
        this.hp = this.hpMax;
        this.speed = template.getSpeed();
        this.damagePower = template.getDamagePower();
        this.premiumKill = template.getKillPremium();
        this.type = template.getMonsterType();
        this.alive = true;
    }

    public void render(SpriteBatch batch) {
        batch.draw(regions[type], position.x - 40, position.y - 40, 40, 40,80,80,
                0.8f,0.8f,0);
    }

    public void renderHUD(SpriteBatch batch, BitmapFont font) {
        batch.draw(textureBackHp, position.x - 30, position.y + 40, 60, 16);
        batch.draw(textureHp, position.x - 30 + 2, position.y + 40 + 2, ((float)hp / hpMax) * 56, 12);
        sbHUD.setLength(0);
        sbHUD.append(hp);
        font.draw(batch, sbHUD, position.x - 30, position.y + 58, 60, 1, false);
    }

    public void update(float dt) {
        position.mulAdd(velocity, dt);

        int cx = (int) (position.x / 80);
        int cy = (int) (position.y / 80);

        float dx = Math.abs(cx * 80 + offsetX - position.x);
        float dy = Math.abs(cy * 80 + offsetY - position.y);

        if (map.isCrossroad(cx, cy) && Vector2.dst(0, 0, dx, dy) < velocity.len() * dt * 2) {
            if (!(lastCellX == cx && lastCellY == cy)) {
                position.set(cx * 80 + offsetX, cy * 80 + offsetY);
                routeCounter++;
                lastCellX = cx;
                lastCellY = cy;
                if (routeCounter > route.getDirections().length - 1) {
                    velocity.set(0, 0);
                    return;
                }
                velocity.set(route.getDirections()[routeCounter].x * speed, route.getDirections()[routeCounter].y * speed);
            }
        }
    }
}
