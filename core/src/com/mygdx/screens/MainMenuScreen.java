package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.Main;
import com.mygdx.hud.MainMenuHud;
import com.mygdx.server.Client;
import com.mygdx.server.Server;

import java.io.IOException;

public class MainMenuScreen implements Screen {
    private Main game;
    private Server server;
    private Client client;
    private MainMenuHud mainMenuHud;

    public MainMenuScreen(Main game) {
        System.out.println("SCREEN Created");
        this.game = game;
        mainMenuHud = new MainMenuHud(this);
        Gdx.input.setInputProcessor(mainMenuHud);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
            System.exit(0);
        }

        Gdx.gl.glClearColor(221, 221, 221, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainMenuHud.update();
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

    public void createServer() throws IOException {
        server = new Server();
        server.start(6666);
    }

    public void startClient() throws IOException {
        client = new Client();
        client.startConnection("localhost", 6666);
    }
}
