package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends com.badlogic.gdx.Game {
    private SpriteBatch batch;
    //private GameScreen gameScreen;
    private MainMenuScreen mainMenuScreen;

    @Override
    public void create() {
        batch = new SpriteBatch();
        //gameScreen = new GameScreen(batch);
        mainMenuScreen = new MainMenuScreen(batch);
        setScreen(mainMenuScreen);
    }

    @Override
    public void render() {
        super.render();
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
            System.exit(0);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void pause() {
    }
}
