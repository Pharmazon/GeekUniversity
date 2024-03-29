package com.td.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.td.game.TowerDefenseGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "TowerDefense Game by Alexey Shcheglov";
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new TowerDefenseGame(), config);
	}
}
