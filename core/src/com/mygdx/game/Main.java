package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.screens.Startmenu;

public class Main extends Game {
    public SpriteBatch batch;
    private Startmenu startmenu;

    @Override
    public void create() {
        batch = new SpriteBatch();
        startmenu = new Startmenu(this);
        setScreen(startmenu);
    }
}
