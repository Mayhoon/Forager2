package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.camera.Camera;
import com.mygdx.entities.player.Player;
import com.mygdx.networking.ServerClientWrapper;

public class Game extends ScreenAdapter {
    private Camera camera;
    private SpriteBatch batch;
    private Player player, player2;

    public Game(ServerClientWrapper serverClientWrapper, SpriteBatch batch) {
        this.batch = batch;
        camera = new Camera();
        camera.zoom -= 0.9f;
        camera.position.x = 0;
        camera.position.y = 0;
        camera.update();

        player = new Player(false, serverClientWrapper, camera);
        player2 = new Player(true, serverClientWrapper, camera);
        Controllers.addListener(player);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
            System.exit(0);
        }

        Gdx.gl.glClearColor(99, 155, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        player2.render(batch);
        player.render(batch);

        batch.end();
        camera.update();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
