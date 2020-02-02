package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.screens.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Crafting Mayhem";
		config.width = 1920;
		config.height = 1080;
		config.resizable = false;
		config.fullscreen = true;
		config.forceExit = true;
		new LwjglApplication(new Game(), config);
	}
}
