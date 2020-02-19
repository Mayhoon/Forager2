package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.camera.Camera;
import com.mygdx.config.Paths;
import com.mygdx.entities.player.Player;
import com.mygdx.stages.hud.InventoryHud;

public class Game extends ScreenAdapter {
    private Texture mapTexture;
    private InventoryHud inventoryHud;
    private Camera camera;
    private SpriteBatch batch;
    private Player player;

    public Game(SpriteBatch batch) {
        mapTexture = new Texture(Paths.TILESET_PATH);
        this.batch = batch;

        camera = new Camera();
        camera.zoom -= 0.9f;
        camera.position.x = 0;
        camera.position.y = 0;
        camera.update();

        player = new Player(camera);
        inventoryHud = new InventoryHud();

        //input listener
        Controllers.addListener(player);
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(inventoryHud);
        Gdx.input.setInputProcessor(inputMultiplexer);
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

        Gdx.gl.glClearColor(99, 155, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);

        System.out.println("YO");
        batch.begin();
        player.render(batch);
        //batch.draw(mapTexture, 0, 0);

        batch.setProjectionMatrix(inventoryHud.getStage().getCamera().combined);
        batch.end();
        inventoryHud.update();
        camera.update();
    }

    @Override
    public void dispose() {
        batch.dispose();
        inventoryHud.dispose();
    }
}
