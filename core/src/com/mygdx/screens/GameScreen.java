package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.camera.Camera;
import com.mygdx.config.CFG;
import com.mygdx.hud.Hud;

public class GameScreen implements Screen {
    private ScreenManager game;
    private Texture texture;
    private Hud hud;
    private Camera camera;
    private SpriteBatch batch;
    private int tileSize;

    public GameScreen(ScreenManager game) {
        this.batch = game.getBatch();
        this.game = game;
        tileSize = CFG.TILESIZE;

        camera = new Camera();
        camera.zoom -= 0.9f;
        camera.position.x = 0;
        camera.position.y = 0;
        camera.update();

        hud = new Hud(this.batch);
        texture = new Texture("maps/Tileset.png");

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(camera);
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
        for(int row=0; row < 40; row++) {
            for (int col=0; col < 40; col++) {
                //batch.draw(texture, row*tileSize,col*tileSize);
            }
        }

        batch.draw(texture, 0, 0);

        game.getBatch().end();
        game.getBatch().setProjectionMatrix(hud.getStage().getCamera().combined);

        hud.update();
        hud.getStage().act();
        hud.getStage().draw();

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
