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

public class LevelCompletedScreen implements Screen {
    private SpriteBatch batch;
    private Stage stage;
    private Skin skin;
    private BitmapFont font32;
    private GameAudio gameAudio;
    private int currentLevelNumber;

    public LevelCompletedScreen(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    public void show() {
        this.font32 = Assets.getInstance().getAssetManager().get(Config.FONTS_PATH + "sketchblock32.ttf",
                BitmapFont.class);
        this.gameAudio = Assets.getInstance().getGameAudio();
        this.currentLevelNumber = Assets.getInstance().getLevelLoader().getCurrentLevelNumber();
        createGUI();
    }

    private void createGUI() {
        this.stage = new Stage(ScreenManager.getInstance().getViewport(), batch);
        Gdx.input.setInputProcessor(stage);
        this.skin = new Skin();
        skin.addRegions(Assets.getInstance().getAtlas());
        skin.add("font32", font32);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("simpleButton");
        textButtonStyle.font = font32;
        skin.add("simpleSkin", textButtonStyle);

        Button btnNextLevel = new TextButton("Next level", skin, "simpleSkin");
        Button btnExitGame = new TextButton("Exit", skin, "simpleSkin");
        btnNextLevel.setPosition(640 - 160, 180);
        btnExitGame.setPosition(640 - 160, 60);
        stage.addActor(btnNextLevel);
        stage.addActor(btnExitGame);

        btnNextLevel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameAudio.buttonClick();
                Assets.getInstance().setLevelNumber(currentLevelNumber + 1);
                ScreenManager.getInstance().changeScreen(ScreenType.GAME);
            }
        });

        btnExitGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameAudio.exitGame();
                Gdx.app.exit();
            }
        });
    }

    public void update(float dt) {
        stage.act(dt);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0.4f, 0.4f, 1.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        font32.draw(batch, "Level completed!", 0, 600, 1280, 1, false);
        batch.end();
        stage.draw();
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
