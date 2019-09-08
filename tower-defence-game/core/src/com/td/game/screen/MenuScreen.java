package com.td.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.td.game.util.Assets;
import com.td.game.util.Config;
import com.td.game.util.GameAudio;
import com.td.game.util.SaveLoadHandler;

public class MenuScreen implements Screen {
    private SpriteBatch batch;
    private Stage stage;
    private Skin skin;
    private BitmapFont font32;
    private BitmapFont font96;
    private SaveLoadHandler saveLoadHandler;
    private GameAudio gameAudio;

    public MenuScreen(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    public void show() {
        this.gameAudio = Assets.getInstance().getGameAudio();
        gameAudio.playMenuScreenMusic();
        this.saveLoadHandler = Assets.getInstance().getSaveLoadHandler();
        this.font32 = Assets.getInstance().getAssetManager().get(Config.FONTS_PATH +
                "sketchblock32.ttf", BitmapFont.class);
        this.font96 = Assets.getInstance().getAssetManager().get(Config.FONTS_PATH +
                "sketchblock96.ttf", BitmapFont.class);
        ScreenManager.getInstance().setGameResumed(false);
        createGUI();
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0.4f, 0.4f, 1.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        font96.draw(batch, "Java Tower Defense", 0, 600, 1280, 1, false);
        batch.end();
        stage.draw();
    }

    public void update(float dt) {
        stage.act(dt);
    }

    public void createGUI() {
        stage = new Stage(ScreenManager.getInstance().getViewport(), batch);
        Gdx.input.setInputProcessor(stage);
        skin = new Skin();
        skin.addRegions(Assets.getInstance().getAtlas());
        skin.add("font32", font32);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("simpleButton");
        textButtonStyle.font = font32;
        skin.add("simpleSkin", textButtonStyle);

        Button btnNewGame = new TextButton("Start New Game", skin, "simpleSkin");
        Button btnResumeGame = new TextButton("Resume game", skin, "simpleSkin");
        Button btnExitGame = new TextButton("Exit Game", skin, "simpleSkin");
        btnNewGame.setPosition(640 - 160, 300);
        btnResumeGame.setPosition(640 - 160, 180);
        btnExitGame.setPosition(640 - 160, 60);
        stage.addActor(btnNewGame);
        stage.addActor(btnExitGame);
        stage.addActor(btnResumeGame);

        btnNewGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                startNewGame();
            }
        });
        btnResumeGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                resumeGame();
            }
        });
        btnExitGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                exitGame();
            }
        });
    }

    private void startNewGame() {
        ScreenManager.getInstance().setGameResumed(false);
        gameAudio.startNewGame();
        ScreenManager.getInstance().changeScreen(ScreenType.GAME);
    }

    private void resumeGame() {
        gameAudio.resumeGame();
        ScreenManager.getInstance().setGameResumed(true);
        ScreenManager.getInstance().changeScreen(ScreenType.GAME);
    }

    private void exitGame() {
        gameAudio.exitGame();
        Gdx.app.exit();
    }

    @Override
    public void resize(int width, int height) {
        ScreenManager.getInstance().resize(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}
