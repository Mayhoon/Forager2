package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "A Game";
		config.width = 500;
		config.height = 500;
		config.resizable = false;
		config.fullscreen =  true;
		config.forceExit = true;
		new LwjglApplication(new Main(), config);
	}
}
