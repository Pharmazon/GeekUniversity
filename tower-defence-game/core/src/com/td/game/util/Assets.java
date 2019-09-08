package com.td.game.util;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.td.game.screen.ScreenType;

public class Assets {
    private static final Assets ourInstance = new Assets();

    public static Assets getInstance() {
        return ourInstance;
    }

    private AssetManager assetManager;
    private TextureAtlas textureAtlas;
    private SaveLoadHandler saveLoadHandler;
    private GameAudio gameAudio;
    private int levelNumber;
    private LevelLoader levelLoader;

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public TextureAtlas getAtlas() {
        return textureAtlas;
    }

    public SaveLoadHandler getSaveLoadHandler() {
        return saveLoadHandler;
    }

    public GameAudio getGameAudio() {
        return gameAudio;
    }

    public LevelLoader getLevelLoader() {
        return levelLoader;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    private Assets() {
        this.assetManager = new AssetManager();
        this.saveLoadHandler = new SaveLoadHandler(Config.SAVES_PATH + "saves.sav");
        this.gameAudio = new GameAudio();
        this.levelLoader = new LevelLoader();
        this.levelNumber = 1;
    }

    public void loadAssets(ScreenType type) {
        assetManager.load(Config.TEXTURES_PATH + "game.pack", TextureAtlas.class);
        switch (type) {
            case MENU:
                createFont(32, "sketchblock");
                createFont(96, "sketchblock");
                break;
            case GAME:
                createFont(15, "zorque");
                createFont(24, "zorque");
                createFont(36, "zorque");
                levelLoader.loadLevel(levelNumber);
                break;
            case GAME_OVER:
                createFont(32, "sketchblock");
                break;
            case LEVEL_COMPLETED:
                createFont(32, "sketchblock");
                break;
            case LEVEL_LOADING:
                createFont(32, "sketchblock");
                break;
        }
    }

    public void createFont(int size, String fontname) {
        FileHandleResolver resolver = new InternalFileHandleResolver();
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
        FreetypeFontLoader.FreeTypeFontLoaderParameter fontParameter =
                new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontParameter.fontFileName = Config.FONTS_PATH + fontname + ".ttf";
        fontParameter.fontParameters.size = size;
        fontParameter.fontParameters.color = Color.WHITE;
        fontParameter.fontParameters.borderWidth = 1;
        fontParameter.fontParameters.borderColor = Color.BLACK;
        fontParameter.fontParameters.shadowOffsetX = 1;
        fontParameter.fontParameters.shadowOffsetY = 1;
        fontParameter.fontParameters.shadowColor = Color.BLACK;
        assetManager.load(Config.FONTS_PATH + fontname + size + ".ttf", BitmapFont.class, fontParameter);
    }

    public void makeLinks() {
        textureAtlas = assetManager.get(Config.TEXTURES_PATH + "game.pack",
                TextureAtlas.class);
    }

    public void clear() {
        assetManager.clear();
    }
}
