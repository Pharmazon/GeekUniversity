package com.td.game.turret;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.td.game.map.Map;
import com.td.game.screen.GameScreen;
import com.td.game.util.Assets;

import java.io.Serializable;

public class TurretEmitter implements Serializable {
    private Map map;
    private Turret[] turrets;
    private TurretTemplate[] templates;
    private transient TextureRegion[] regions;

    public Turret[] getTurrets() {
        return turrets;
    }

    public void setRegions(TextureRegion[] regions) {
        this.regions = regions;
    }

    public TurretEmitter(GameScreen gameScreen, Map map, int maxSize) {
        this.templates = Assets.getInstance().getLevelLoader().getTurretTemplates();
        this.map = map;
        this.turrets = new Turret[maxSize];
        this.regions = new TextureRegion(
                Assets.getInstance().getAtlas().findRegion("turrets")).split(80, 80)[0];
        for (int i = 0; i < turrets.length; i++) {
            turrets[i] = new Turret(regions, gameScreen, map, 0, 0);
        }
    }

    public void render(SpriteBatch batch, BitmapFont font) {
        for (int i = 0; i < turrets.length; i++) {
            if (turrets[i].isActive()) {
                turrets[i].render(batch);
                turrets[i].renderHUD(batch, font);
            }
        }
    }

    public void update(float dt) {
        for (int i = 0; i < turrets.length; i++) {
            if (turrets[i].isActive()) {
                turrets[i].update(dt);
            }
        }
    }

    public int getTurretCostConstruction(int index) {
        return templates[index].getCostConstruction();
    }

    public int getTurretPremiumDestruction(int cellX, int cellY) {
        return templates[typeTurretInCell(cellX, cellY)].getPremiumDestruction();
    }

    public boolean setTurret(int index, int cellX, int cellY) {
        if (map.isGrass(cellX, cellY) && !isTurretExistsInCell(cellX, cellY)) {
            for (int i = 0; i < turrets.length; i++) {
                if (!turrets[i].isActive()) {
                    turrets[i].activate(templates[index], cellX, cellY);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean upgradeTurret(int cellX, int cellY) {
        if (isTurretExistsInCell(cellX, cellY)) {
            for (int i = 0; i < turrets.length; i++) {
                if (turrets[i].isActive() && turrets[i].getCellX() == cellX && turrets[i].getCellY() == cellY) {
                    if (turrets[i].getType() < templates.length - 1) {
                        turrets[i].deactivate();
                        turrets[i].activate(templates[turrets[i].getType() + 1], cellX, cellY);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void destroyTurret(int cellX, int cellY) {
        if (isTurretExistsInCell(cellX, cellY)) {
            for (int i = 0; i < turrets.length; i++) {
                if (turrets[i].isActive() && turrets[i].getCellX() == cellX && turrets[i].getCellY() == cellY) {
                    turrets[i].deactivate();
                }
            }
        }
    }

    public boolean isTurretExistsInCell(int cellX, int cellY) {
        for (int i = 0; i < turrets.length; i++) {
            if (turrets[i].isActive() && turrets[i].getCellX() == cellX && turrets[i].getCellY() == cellY) {
                return true;
            }
        }
        return false;
    }

    public int typeTurretInCell(int cellX, int cellY) {
        if (isTurretExistsInCell(cellX, cellY)) {
            for (int i = 0; i < turrets.length; i++) {
                if (turrets[i].isActive() && turrets[i].getCellX() == cellX && turrets[i].getCellY() == cellY) {
                    return turrets[i].getType();
                }
            }
        }
        return -1;
    }

    public int nextTypeTurretInCell(int cellX, int cellY) {
        if (typeTurretInCell(cellX, cellY) + 1 < templates.length - 1) {
            return typeTurretInCell(cellX, cellY) + 1;
        }
        return typeTurretInCell(cellX, cellY);
    }
}

