package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.screens.MainMenuScreen;

public class Main extends Game {
    public SpriteBatch batch;
   // private GameScreen gameScreen;

    @Override
    public void create() {
        batch = new SpriteBatch();
//        gameScreen = new GameScreen(batch);
        MainMenuScreen mainMenuScreen = new MainMenuScreen(this);
        setScreen(mainMenuScreen);
    }
}
