package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.screens.GameScreen;
import com.mygdx.screens.MainMenuScreen;

public class Main extends Game {
    public SpriteBatch batch;
    private MainMenuScreen mainMenuScreen;
    private GameScreen gameScreen;

    @Override
    public void create() {
        batch = new SpriteBatch();
        gameScreen = new GameScreen(batch);
        mainMenuScreen = new MainMenuScreen(this);
        setScreen(mainMenuScreen);
    }
}
