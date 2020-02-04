package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.screens.MainMenuScreen;

public class Main extends Game {
    public SpriteBatch batch;
    private MainMenuScreen mainMenuScreen;

    public Main() {
        batch = new SpriteBatch();
        mainMenuScreen = new MainMenuScreen(this);
    }

    @Override
    public void create() {
        setScreen(mainMenuScreen);
    }

    public void render() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
            System.exit(0);
        }
    }
}
