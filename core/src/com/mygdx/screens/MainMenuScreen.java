package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.Main;
import com.mygdx.screens.transitions.TransitionManager;
import com.mygdx.server.Client;
import com.mygdx.server.Server;
import com.mygdx.stages.hud.MainMenuHud;
import com.mygdx.stages.ownStage;

import java.io.IOException;

public class MainMenuScreen extends TransitionManager implements Screen {
    private Main game;
    private ownStage currentStage;
    private Server server;
    private Client client;
    private MainMenuHud mainMenuHud;

    public MainMenuScreen(Main game) {
        mainMenuHud = new MainMenuHud(this);
        Gdx.input.setInputProcessor(mainMenuHud);
        this.game = game;
        currentStage = mainMenuHud;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(221, 221, 221, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
            System.exit(0);
        }
        currentStage.update();
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

    }

    public void startServer() {
        Thread serverThread = new Thread(new Server());
        serverThread.start();
    }

    public void startClient() throws IOException {
       Thread clientThread = new Thread(new Client());
        clientThread.start();
    }
}
