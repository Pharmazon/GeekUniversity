package com.td.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.td.game.screen.GameScreen;

import java.io.*;

public class SaveLoadHandler {
    private FileHandle file;
    private String filename;

    public SaveLoadHandler(String filename) {
        this.filename = filename;
        this.file = Gdx.files.local(filename);
    }

    public void saveGame(Serializable data) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            if (file.exists()) file.delete();
            fos = new FileOutputStream(filename);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.flush();
        } catch (IOException e) {
            Assets.getInstance().getAssetManager().getLogger().error("Couldn't write object " + data);
        } finally {
            try {
                oos.close();
                fos.close();
            } catch (IOException e) {
                Assets.getInstance().getAssetManager().getLogger().error("Couldn't close output streams for " +
                        "writing object " + data);
            }
        }
    }

    public GameScreen loadGame() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            if (file.exists()) {
                fis = new FileInputStream(filename);
                ois = new ObjectInputStream(fis);
                return (GameScreen) ois.readObject();
            }
        } catch (IOException e) {
            Assets.getInstance().getAssetManager().getLogger().error("Couldn't read object from file");
        } catch (ClassNotFoundException e) {
            Assets.getInstance().getAssetManager().getLogger().error("Couldn't close input streams for " +
                    "reading object");
        } finally {
            try {
                ois.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
