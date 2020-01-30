package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.camera.Camera;
import com.mygdx.config.Paths;
import com.mygdx.hud.Hud;
import com.mygdx.input.ControllerInput;
import com.mygdx.player.Player;

public class GameScreen implements Screen {
    private Game game;
    private Texture texture;
    private Hud hud;
    private Camera camera;
    private SpriteBatch batch;
    private Player player;
    private ControllerInput controllerInput;

    public GameScreen(Game game) {
        this.batch = game.getBatch();
        this.game = game;

        camera = new Camera();
        camera.zoom -= 0.9f;
        camera.position.x = 0;
        camera.position.y = 0;
        camera.update();

        controllerInput = new ControllerInput(camera);
        player = new Player(camera);

        hud = new Hud(this.batch);
        texture = new Texture(Paths.TILESET_PATH);

        Controllers.addListener(controllerInput);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(player.getProcessor());
        inputMultiplexer.addProcessor(hud);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(99,155,255,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);

        game.getBatch().begin();
        player.render(batch);
        batch.draw(texture, 0, 0);
        game.getBatch().end();
        game.getBatch().setProjectionMatrix(hud.getStage().getCamera().combined);

        hud.update();
        camera.update();
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
        hud.dispose();
    }
}
