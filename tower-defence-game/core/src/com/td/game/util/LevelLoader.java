package com.td.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Logger;
import com.td.game.map.CellType;
import com.td.game.map.Route;
import com.td.game.monster.MonsterTemplate;
import com.td.game.monster.Wave;
import com.td.game.turret.TurretTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelLoader {
    private CellType[][] data;
    private List<Route> routes;
    private TurretTemplate[] turretTemplates;
    private Wave[] waves;
    private MonsterTemplate[] monsterTemplates;
    private int worldWidthInCells;
    private int worldHeightInCells;
    private int currentLevelNumber;
    private Logger logger;

    public int getCurrentLevelNumber() {
        return currentLevelNumber;
    }

    public CellType[][] getData() {
        return data;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public TurretTemplate[] getTurretTemplates() {
        return turretTemplates;
    }

    public Wave[] getWaves() {
        return waves;
    }

    public MonsterTemplate[] getMonsterTemplates() {
        return monsterTemplates;
    }

    public int getWorldWidthInCells() {
        return worldWidthInCells;
    }

    public int getWorldHeightInCells() {
        return worldHeightInCells;
    }

    public void loadLevel(int levelNumber) {
        loadMapFromFile(levelNumber);
        loadScenarioFromFile(levelNumber);
        loadMonstersFromFile(levelNumber);
        loadTurretsFromFile(levelNumber);
        this.currentLevelNumber = levelNumber;
        this.logger = Assets.getInstance().getAssetManager().getLogger();
    }

    private void loadMapFromFile(int levelNumber) {
        BufferedReader br = null;
        List<String> lines = new ArrayList<String>();
        try {
            br = Gdx.files.internal(Config.LEVELS_PATH + levelNumber + "/" +
                    Config.MAP_PREFIX + levelNumber + ".dat").reader(8192);
            String str;
            while ((str = br.readLine()) != null) {
                lines.add(str);
            }
        } catch (IOException e) {
            logger.error("Couldn't load map" + levelNumber + ".dat");
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                logger.error("Couldn't close the reader stream during loading map" +
                        levelNumber + ".dat");
            }
        }
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equals("routes")) {
                this.worldHeightInCells = i;
                break;
            }
        }
        this.worldWidthInCells = lines.get(0).split(",").length;
        this.data = new CellType[worldWidthInCells][worldHeightInCells];
        for (int i = 0; i < worldHeightInCells; i++) {
            String[] arr = lines.get(i).split(",");
            for (int j = 0; j < worldWidthInCells; j++) {
                data[j][worldHeightInCells - i - 1] = CellType.getTypeFromString(arr[j]);
            }
        }
        this.routes = new ArrayList<Route>();
        for (int i = worldHeightInCells + 1; i < lines.size(); i++) {
            routes.add(new Route(lines.get(i)));
        }
    }

    private void loadTurretsFromFile(int levelNumber) {
        BufferedReader br = null;
        List<String> lines = new ArrayList<String>();
        try {
            br = Gdx.files.internal(Config.LEVELS_PATH + levelNumber + "/" +
                    Config.TURRETS_PREFIX + levelNumber + ".dat").reader(8192);
            String str;
            while ((str = br.readLine()) != null) {
                lines.add(str);
            }
        } catch (IOException e) {
            logger.error("Couldn't load turrets" + levelNumber + ".dat");
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                logger.error("Couldn't close the reader stream during loading turrets" +
                        levelNumber + ".dat");
            }
        }
        this.turretTemplates= new TurretTemplate[lines.size() - 1];
        for (int i = 1; i < lines.size(); i++) {
            turretTemplates[i - 1] = new TurretTemplate(lines.get(i));
        }
    }

    private void loadScenarioFromFile(int levelNumber) {
        BufferedReader br = null;
        List<String> lines = new ArrayList<String>();
        try {
            br = Gdx.files.internal(Config.LEVELS_PATH + levelNumber + "/" +
                    Config.SCENARIO_PREFIX + levelNumber + ".dat").reader(8192);
            String str;
            while ((str = br.readLine()) != null) {
                lines.add(str);
            }
        } catch (IOException e) {
            logger.error("Couldn't load scenario" + levelNumber + ".dat");
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                logger.error("Couldn't close the reader stream during loading scenario" +
                        levelNumber + ".dat");
            }
        }
        lines.remove(0);
        this.waves = new Wave[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            waves[i] = new Wave(lines.get(i));
        }
    }

    private void loadMonstersFromFile(int levelNumber) {
        BufferedReader br = null;
        List<String> lines = new ArrayList<String>();
        try {
            br = Gdx.files.internal(Config.LEVELS_PATH + levelNumber + "/" +
                    Config.MONSTERS_PREFIX + levelNumber + ".dat").reader(8192);
            String str;
            while ((str = br.readLine()) != null) {
                lines.add(str);
            }
        } catch (IOException e) {
            logger.error("Couldn't load monsters" + levelNumber + ".dat");
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                logger.error("Couldn't close the reader stream during loading monsters" +
                        levelNumber + ".dat");
            }
        }
        this.monsterTemplates = new MonsterTemplate[lines.size() - 1];
        for (int i = 1; i < lines.size(); i++) {
            monsterTemplates[i - 1] = new MonsterTemplate(lines.get(i));
        }
    }
}
