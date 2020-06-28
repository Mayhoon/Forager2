package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import game.Main;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.title = "Game";


//        config.width = 1920;
//        config.height = 1080;
//        config.fullscreen = true;


        config.width = 800;
        config.height = 800;
        config.fullscreen = false;


        config.resizable = false;
        config.forceExit = true;
        new LwjglApplication(new Main(), config);
    }
}
