package com.td.game.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.td.game.util.Assets;

import java.io.Serializable;

public class Map implements Serializable {
    private CellType[][] data;
    transient private TextureRegion textureGrass;
    transient private TextureRegion textureRoad;

    public void setTextureGrass(TextureRegion textureGrass) {
        this.textureGrass = textureGrass;
    }

    public void setTextureRoad(TextureRegion textureRoad) {
        this.textureRoad = textureRoad;
    }

    public Map() {
        this.textureGrass = Assets.getInstance().getAtlas().findRegion("grass");
        this.textureRoad = Assets.getInstance().getAtlas().findRegion("road");
        this.data = Assets.getInstance().getLevelLoader().getData();
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < Assets.getInstance().getLevelLoader().getWorldWidthInCells(); i++) {
            for (int j = 0; j < Assets.getInstance().getLevelLoader().getWorldHeightInCells(); j++) {
                if (data[i][j] == CellType.GRASS) {
                    batch.draw(textureGrass, i * 80, j * 80);
                }
                if (data[i][j] == CellType.ROAD || data[i][j] == CellType.CROSSROAD) {
                    batch.draw(textureRoad, i * 80, j * 80);
                }
            }
        }
    }

    public boolean isCrossroad(int cx, int cy) {
        return data[cx][cy] == CellType.CROSSROAD;
    }

    public boolean isHome(int cx, int cy) {
        return data[cx][cy] == CellType.HOME;
    }

    public boolean isGrass(int cx, int cy) {
        return data[cx][cy] == CellType.GRASS;
    }
}
