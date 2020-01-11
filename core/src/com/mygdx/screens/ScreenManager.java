package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sun.rmi.rmic.Main;

public class ScreenManager extends Game {
    private SpriteBatch batch;
    private GameScreen gameScreen;
    private MainMenuScreen mainMenuScreen;

    @Override
    public void create() {
        batch = new SpriteBatch();
        gameScreen = new GameScreen(this);
        mainMenuScreen = new MainMenuScreen(this);
        setScreen(gameScreen);
    }

    @Override
    public void render() {
        super.render();

        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            setScreen(mainMenuScreen);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
            setScreen(gameScreen);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
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

    public SpriteBatch getBatch() {
        return batch;
    }
}
