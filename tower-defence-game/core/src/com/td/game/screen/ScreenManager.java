package com.td.game.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.td.game.TowerDefenseGame;
import com.td.game.util.Assets;

public class ScreenManager {
    public static final int WORLD_WIDTH = 1280;
    public static final int WORLD_HEIGHT = 720;

    private TowerDefenseGame game;
    private SpriteBatch batch;
    private GameScreen gameScreen;
    private LoadingScreen loadingScreen;
    private MenuScreen menuScreen;
    private GameOverScreen gameOverScreen;
    private LevelCompletedScreen levelCompletedScreen;
    private Screen targetScreen;
    private Viewport viewport;
    private Camera camera;
    private boolean isGameResumed;

    private static ScreenManager ourInstance = new ScreenManager();

    public static ScreenManager getInstance() {
        return ourInstance;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public boolean isGameResumed() {
        return isGameResumed;
    }

    public void setGameResumed(boolean gameResumed) {
        isGameResumed = gameResumed;
    }

    private ScreenManager() {
    }

    public void init(TowerDefenseGame game, SpriteBatch batch) {
        this.game = game;
        this.batch = batch;
        this.camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        this.viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        this.gameScreen = new GameScreen(batch, camera);
        this.menuScreen = new MenuScreen(batch);
        this.loadingScreen = new LoadingScreen(batch);
        this.gameOverScreen = new GameOverScreen(batch);
        this.levelCompletedScreen = new LevelCompletedScreen(batch);
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
        viewport.apply();
    }

    public void resetCamera() {
        camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    public void changeScreen(ScreenType type) {
        Screen screen = game.getScreen();
        Assets.getInstance().clear();
        if (screen != null) {
            screen.dispose();
        }
        resetCamera();
        game.setScreen(loadingScreen);
        switch (type) {
            case MENU:
                targetScreen = menuScreen;
                Assets.getInstance().loadAssets(ScreenType.MENU);
                break;
            case GAME:
                targetScreen = gameScreen;
                Assets.getInstance().loadAssets(ScreenType.GAME);
                break;
            case GAME_OVER:
                targetScreen = gameOverScreen;
                Assets.getInstance().loadAssets(ScreenType.GAME_OVER);
                break;
            case LEVEL_COMPLETED:
                targetScreen = levelCompletedScreen;
                Assets.getInstance().loadAssets(ScreenType.LEVEL_COMPLETED);
                break;
        }
    }

    public void goToTarget() {
        game.setScreen(targetScreen);
    }
}
