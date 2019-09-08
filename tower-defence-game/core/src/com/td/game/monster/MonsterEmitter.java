package com.td.game.monster;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.td.game.map.Map;
import com.td.game.util.Assets;

import java.io.Serializable;

public class MonsterEmitter implements Serializable {
    private Monster[] monsters;
    private float spawnTimer;
    private int currentWave;
    private Wave[] waves;
    private MonsterTemplate[] templates;
    private boolean isWavesFinished;
    private transient TextureRegion[] regions;

    public Monster[] getMonsters() {
        return monsters;
    }

    public boolean isWavesFinished() {
        return isWavesFinished;
    }

    public void setRegions(TextureRegion[] regions) {
        this.regions = regions;
    }

    public MonsterEmitter(Map map, int maxSize) {
        this.templates = Assets.getInstance().getLevelLoader().getMonsterTemplates();
        this.monsters = new Monster[maxSize];
        this.waves = Assets.getInstance().getLevelLoader().getWaves();
        this.isWavesFinished = false;
        this.regions = new TextureRegion(
                Assets.getInstance().getAtlas().findRegion("monsters")).split(80, 80)[0];
        for (int i = 0; i < monsters.length; i++) {
            this.monsters[i] = new Monster(regions, map, 0);
        }
    }

    public void createMonster(int monsterType, int routeIndex) {
        for (int i = 0; i < monsters.length; i++) {
            if (!monsters[i].isAlive()) {
                monsters[i].activate(templates[monsterType], routeIndex);
                break;
            }
        }
    }

    public void render(SpriteBatch batch, BitmapFont font) {
        for (int i = 0; i < monsters.length; i++) {
            if (monsters[i].isAlive()) {
                monsters[i].render(batch);
                monsters[i].renderHUD(batch, font);
            }
        }
    }

    public void update(float dt) {
        if (currentWave < waves.length) {
            spawnTimer += dt;
            Wave w = waves[currentWave];
            if (spawnTimer >= w.getTime()) {
                for (int j = 0; j < w.getMonstersCount(); j++) {
                    createMonster(w.getMonsterType(), w.getRouteIndex());
                }
                currentWave++;
            }
        }
        for (int i = 0; i < monsters.length; i++) {
            if (monsters[i].isAlive()) {
                monsters[i].update(dt);
            }
        }
        if (currentWave >= waves.length) isWavesFinished = true;
    }
}
