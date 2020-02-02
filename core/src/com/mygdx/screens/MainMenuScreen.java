package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.hud.MainMenuHud;

public class MainMenuScreen implements Screen {
    SpriteBatch batch;
    private MainMenuHud mainMenuHut;

    public MainMenuScreen(SpriteBatch batch) {
        this.batch = batch;
        mainMenuHut = new MainMenuHud();
        Gdx.input.setInputProcessor(mainMenuHut);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(221, 221, 221, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainMenuHut.update();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
