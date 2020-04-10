package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Pixelworld";
		config.width = 800;
		config.height = 800;
		config.resizable = false;
		config.fullscreen =  false;
		config.forceExit = true;
		new LwjglApplication(new Main(), config);
	}
}
