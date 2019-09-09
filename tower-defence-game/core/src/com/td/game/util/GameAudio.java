package com.td.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.io.File;
import java.io.FilenameFilter;

public class GameAudio {
    private Sound[] sounds;
    private Sound[] shootSounds;
    private Music[] music;

    public GameAudio() {
        readSoundsFromFolder();
        readMusicFromFolder();
    }

    private void readSoundsFromFolder() {
        File folderSounds = new File(Config.SOUNDS_PATH);
        String[] filesSound = folderSounds.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("shoot") && name.endsWith(".mp3");
            }
        });
        this.shootSounds = new Sound[filesSound.length];
        for (int i = 0; i < shootSounds.length; i++) {
            shootSounds[i] = Gdx.audio.newSound(Gdx.files.internal(Config.SOUNDS_PATH + filesSound[i]));
        }

        filesSound = folderSounds.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return !name.startsWith("shoot") && name.endsWith(".mp3");
            }
        });
        this.sounds = new Sound[filesSound.length];
        for (int i = 0; i < sounds.length; i++) {
            sounds[i] = Gdx.audio.newSound(Gdx.files.internal(Config.SOUNDS_PATH + filesSound[i]));
        }

    }

    private void readMusicFromFolder() {
        File folderMusic = new File(Config.MUSIC_PATH);
        String[] filesMusic = folderMusic.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".mp3");
            }
        });
        this.music = new Music[filesMusic.length];
        for (int i = 0; i < music.length; i++) {
            music[i] = Gdx.audio.newMusic(Gdx.files.internal(Config.MUSIC_PATH + filesMusic[i]));
            music[i].setLooping(true);
            music[i].setVolume(0.8f);
        }
    }

    public void buttonClick() {
        sounds[0].play();
    }

    public void setTurret() {
        sounds[1].play();
    }

    public void upgradeTurret() {
        sounds[2].play();
    }

    public void destroyTurret() {
        sounds[3].play();
    }

    public void damageHome() {
        sounds[4].play();
    }

    public void killMonster() {
        sounds[5].play();
    }

    public void levelCompleted() {
        stopAllAudio();
        sounds[6].play();
    }

    public void gameOver() {
        stopAllAudio();
        sounds[7].play();
    }

    public void shoot(int index) {
        shootSounds[index].play();
    }

    public void stopAllAudio() {
        for (int i = 0; i < music.length; i++) {
            if (music[i].isPlaying()) music[i].stop();
        }
        for (int i = 0; i < sounds.length; i++) {
            sounds[i].stop();
        }
        for (int i = 0; i < shootSounds.length; i++) {
            shootSounds[i].stop();
        }
    }

    public void playMenuScreenMusic() {
        stopAllAudio();
        music[0].play();
    }

    public void playGameScreenMusic() {
        stopAllAudio();
        music[1].play();
    }

    public void exitGame() {
        stopAllAudio();
        buttonClick();
    }

    public void resumeGame() {
        stopAllAudio();
        buttonClick();
    }

    public void startNewGame() {
        stopAllAudio();
        buttonClick();
    }

    public void back() {
        stopAllAudio();
        buttonClick();
    }
}
