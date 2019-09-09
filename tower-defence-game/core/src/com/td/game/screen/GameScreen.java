package com.td.game.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.td.game.bullet.ParticleEmitter;
import com.td.game.gui.PlayerInfo;
import com.td.game.gui.UpperPanel;
import com.td.game.map.Map;
import com.td.game.monster.Monster;
import com.td.game.monster.MonsterEmitter;
import com.td.game.turret.TurretEmitter;
import com.td.game.util.Assets;
import com.td.game.util.Config;
import com.td.game.util.GameAudio;
import com.td.game.util.SaveLoadHandler;

import java.io.Serializable;

public class GameScreen implements Screen, Serializable {
    private transient SaveLoadHandler saveLoadHandler;
    private transient SpriteBatch batch;
    private transient BitmapFont font24;
    private transient BitmapFont font15;
    private transient TextureAtlas atlas;
    private transient TextureRegion selectedCellTexture;
    private transient Stage stage;
    private transient Group groupTurretAction;
    private transient Camera camera;
    private transient GameAudio gameAudio;
    private transient int selectedCellX, selectedCellY;
    private transient GameScreen loadedGameScreen;

    private Map map;
    private TurretEmitter turretEmitter;
    private MonsterEmitter monsterEmitter;
    private PlayerInfo playerInfo;
    private UpperPanel upperPanel;
    private Vector2 mousePosition;
    private ParticleEmitter particleEmitter;

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public ParticleEmitter getParticleEmitter() {
        return particleEmitter;
    }

    public MonsterEmitter getMonsterEmitter() {
        return monsterEmitter;
    }

    public TurretEmitter getTurretEmitter() {
        return turretEmitter;
    }

    public Map getMap() {
        return map;
    }

    public GameScreen(SpriteBatch batch, Camera camera) {
        this.batch = batch;
        this.camera = camera;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(null);
        this.saveLoadHandler = Assets.getInstance().getSaveLoadHandler();
        this.gameAudio = Assets.getInstance().getGameAudio();
        this.atlas = Assets.getInstance().getAtlas();
        this.selectedCellTexture = atlas.findRegion("cursor");
        this.stage = new Stage(ScreenManager.getInstance().getViewport(), batch);
        this.font24 = Assets.getInstance().getAssetManager().get(Config.FONTS_PATH +
                "zorque24.ttf", BitmapFont.class);
        this.font15 = Assets.getInstance().getAssetManager().get(Config.FONTS_PATH +
                "zorque15.ttf", BitmapFont.class);
        this.mousePosition = new Vector2(0, 0);

        if (ScreenManager.getInstance().isGameResumed()) {
            initGameResume();
        } else {
            initNewGame();
        }
        createGUI();
        gameAudio.playGameScreenMusic();
    }

    private void initGameResume() {
        this.loadedGameScreen = saveLoadHandler.loadGame();

        this.monsterEmitter = loadedGameScreen.getMonsterEmitter();
        this.turretEmitter = loadedGameScreen.getTurretEmitter();
        this.playerInfo = loadedGameScreen.getPlayerInfo();
        this.particleEmitter = loadedGameScreen.getParticleEmitter();
        particleEmitter.setOneParticle(atlas.findRegion("particle"));
        this.map = loadedGameScreen.getMap();
        map.setTextureGrass(Assets.getInstance().getAtlas().findRegion("grass"));
        map.setTextureRoad(Assets.getInstance().getAtlas().findRegion("road"));

        TextureRegion[] regionsTurrets = new TextureRegion(
                Assets.getInstance().getAtlas().findRegion("turrets")).split(80, 80)[0];
        turretEmitter.setRegions(regionsTurrets);
        for (int i = 0; i < turretEmitter.getTurrets().length; i++) {
            turretEmitter.getTurrets()[i].setRegions(regionsTurrets);
            turretEmitter.getTurrets()[i].setGameAudio(gameAudio);
        }
        TextureRegion[] regionsMonsters = new TextureRegion(
                Assets.getInstance().getAtlas().findRegion("monsters")).split(80, 80)[0];
        monsterEmitter.setRegions(regionsMonsters);
        for (int i = 0; i < monsterEmitter.getMonsters().length; i++) {
            monsterEmitter.getMonsters()[i].setRegions(regionsMonsters);
            monsterEmitter.getMonsters()[i].setTextureHp(Assets.getInstance().getAtlas().
                    findRegion("monsterHp"));
            monsterEmitter.getMonsters()[i].setTextureBackHp(Assets.getInstance().getAtlas().
                    findRegion("monsterBackHP"));
        }
    }

    public void initNewGame() {
        this.map = new Map();
        this.turretEmitter = new TurretEmitter(this, map, 20);
        this.monsterEmitter = new MonsterEmitter(map, 100);
        this.playerInfo = new PlayerInfo(500, 32);
        this.particleEmitter = new ParticleEmitter(atlas.findRegion("particle"));
    }

    private void createGUI() {
        InputProcessor myProc = new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                selectedCellX = (int) (mousePosition.x / 80);
                selectedCellY = (int) (mousePosition.y / 80);
                return true;
            }
        };
        InputMultiplexer im = new InputMultiplexer(stage, myProc);
        Gdx.input.setInputProcessor(im);

        Skin skin = new Skin();
        skin.addRegions(Assets.getInstance().getAtlas());

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("shortButton");
        textButtonStyle.font = font24;
        skin.add("simpleSkin", textButtonStyle);

        groupTurretAction = new Group();
        groupTurretAction.setPosition(10, 10);

        Button btnSetTurret = new TextButton("Set", skin, "simpleSkin");
        Button btnUpgradeTurret = new TextButton("Upg", skin, "simpleSkin");
        Button btnDestroyTurret = new TextButton("Dst", skin, "simpleSkin");
        btnSetTurret.setPosition(10, 10);
        btnUpgradeTurret.setPosition(110, 10);
        btnDestroyTurret.setPosition(210, 10);
        groupTurretAction.addActor(btnSetTurret);
        groupTurretAction.addActor(btnUpgradeTurret);
        groupTurretAction.addActor(btnDestroyTurret);

        btnSetTurret.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setBasicTurret();
            }
        });
        btnUpgradeTurret.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                upgradeTurret();
            }
        });
        btnDestroyTurret.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                destroyTurret();
            }
        });

        Button btnBack = new TextButton("Back", skin, "simpleSkin");
        btnBack.setPosition(1180, 20);
        btnBack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                back();
            }
        });

        stage.addActor(groupTurretAction);
        stage.addActor(btnBack);

        this.upperPanel = new UpperPanel(playerInfo, stage, 0, 720 - 60);

        skin.dispose();
    }

    private void setBasicTurret() {
        if (playerInfo.isMoneyEnough(turretEmitter.getTurretCostConstruction(0))) {
            if (turretEmitter.setTurret(0, selectedCellX, selectedCellY)) {
                playerInfo.decreaseMoney(turretEmitter.getTurretCostConstruction(0));
                gameAudio.setTurret();
            }
        }
    }

    private void upgradeTurret() {
        if (playerInfo.isMoneyEnough(turretEmitter.getTurretCostConstruction(
                        turretEmitter.nextTypeTurretInCell(selectedCellX, selectedCellY)))) {
            if (turretEmitter.upgradeTurret(selectedCellX, selectedCellY)) {
                playerInfo.decreaseMoney(turretEmitter.getTurretCostConstruction(
                        turretEmitter.nextTypeTurretInCell(selectedCellX, selectedCellY)));
                gameAudio.upgradeTurret();
            }
        }
    }

    private void destroyTurret() {
        playerInfo.addMoney(turretEmitter.getTurretPremiumDestruction(selectedCellX, selectedCellY));
        turretEmitter.destroyTurret(selectedCellX, selectedCellY);
        gameAudio.destroyTurret();
    }


    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.position.set(640 + 160, 360, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        map.render(batch);
        turretEmitter.render(batch, font15);
        monsterEmitter.render(batch, font24);
        particleEmitter.render(batch);
        batch.setColor(1, 1, 0, 0.5f);
        batch.draw(selectedCellTexture, selectedCellX * 80, selectedCellY * 80);
        batch.setColor(1, 1, 1, 1);
        batch.end();
        camera.position.set(640, 360, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        stage.draw();
    }

    public void update(float dt) {
        camera.position.set(640 + 160, 360, 0);
        camera.update();
        ScreenManager.getInstance().getViewport().apply();
        mousePosition.set(Gdx.input.getX(), Gdx.input.getY());
        ScreenManager.getInstance().getViewport().unproject(mousePosition);
        monsterEmitter.update(dt);
        turretEmitter.update(dt);
        particleEmitter.update(dt);
        particleEmitter.checkPool();
        upperPanel.update();
        checkMonstersAtHome();
        checkGameOver();
        checkLevelCompleted();
        stage.act(dt);
    }

    private void checkMonstersAtHome() {
        for (int i = 0; i < monsterEmitter.getMonsters().length; i++) {
            Monster m = monsterEmitter.getMonsters()[i];
            if (m.isAlive()) {
                if (map.isHome(m.getCellX(), m.getCellY())) {
                    gameAudio.damageHome();
                    m.deactivate();
                    playerInfo.decreaseHp(m.getDamagePower());
                }
            }
        }
    }

    private void checkGameOver() {
        if (playerInfo.isGameOver()) {
            gameAudio.gameOver();
            ScreenManager.getInstance().setGameResumed(false);
            ScreenManager.getInstance().changeScreen(ScreenType.GAME_OVER);
        }
    }

    private void checkLevelCompleted() {
        boolean flag = false;
        if (monsterEmitter.isWavesFinished()) {
            for (int i = 0; i < monsterEmitter.getMonsters().length; i++) {
                Monster m = monsterEmitter.getMonsters()[i];
                if (m.isAlive()) flag = true;
            }
            if (!flag) {
                gameAudio.levelCompleted();
                ScreenManager.getInstance().setGameResumed(false);
                ScreenManager.getInstance().changeScreen(ScreenType.LEVEL_COMPLETED);
            }
        }
    }

    private void back() {
        gameAudio.back();
        saveLoadHandler.saveGame(this);
        ScreenManager.getInstance().changeScreen(ScreenType.MENU);
    }

    @Override
    public void resize(int width, int height) {
        ScreenManager.getInstance().resize(width, height);
    }

    @Override
    public void pause() {
        saveLoadHandler.saveGame(this);
    }

    @Override
    public void resume() {
        initGameResume();
    }

    @Override
    public void hide() {
        saveLoadHandler.saveGame(this);
    }

    @Override
    public void dispose() {
    }
}
