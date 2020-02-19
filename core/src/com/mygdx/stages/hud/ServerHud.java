package com.mygdx.stages.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.config.Paths;
import com.mygdx.networking.Server;
import com.mygdx.stages.customStage;
import com.mygdx.tools.FontLoader;

public class ServerHud extends customStage {
    private FontLoader fontLoader;
    private BitmapFont font;
    private Table table;

    public ServerHud(SpriteBatch batch) {
        super(batch);

        table = new Table();
        fontLoader = new FontLoader();
        font = fontLoader.loadFont(Paths.ITEM_COUNT_FONT, 32, Color.LIME);


    }

    public void startServer() {
        Thread serverThread = new Thread(new Server());
        serverThread.start();
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        font.draw(batch, "Hi", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
    }
}
