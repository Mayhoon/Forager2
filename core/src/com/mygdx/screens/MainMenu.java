package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.Main;
import com.mygdx.stages.hud.MainMenuHud;
import com.mygdx.stages.transitions.StageManager;

public class MainMenu extends ScreenAdapter {
    private Main game;
    public StageManager stageManager;

    public MainMenu(Main game) {
        stageManager = new StageManager(new MainMenuHud(this));
        this.game = game;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(221, 221, 221, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
            System.exit(0);
        }
        stageManager.updateCurrentStage(game.batch);
    }
}
