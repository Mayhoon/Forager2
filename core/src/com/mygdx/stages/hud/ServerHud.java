package com.mygdx.stages.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.config.Paths;
import com.mygdx.game.Main;
import com.mygdx.networking.Server;
import com.mygdx.screens.Game;
import com.mygdx.stages.customStage;
import com.mygdx.tools.FontLoader;

public class ServerHud extends customStage {
    private FontLoader fontLoader;
    private BitmapFont font;
    private ImageButton hostButton;
    public String connectionStatus;
    private GlyphLayout glyphLayout;
    private Main game;

    public ServerHud(Main game) {
        super(game.batch);
        this.game = game;

        Gdx.input.setInputProcessor(this);
        glyphLayout = new GlyphLayout();
        connectionStatus = "";

        Table table = new Table();
        table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        fontLoader = new FontLoader();
        font = fontLoader.loadFont(Paths.ITEM_COUNT_FONT, 32, Color.BLACK);

        Drawable hostButtonDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(Paths.HOST_BUTTON))));
        Drawable hostButtonHoveredDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(Paths.HOST_HOVERED_BUTTON))));
        hostButton = new ImageButton(hostButtonDrawable, hostButtonHoveredDrawable);
        hostButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int a, int b) {
                startServer();
                return true;
            }
        });
        table.add(hostButton).center();
        addActor(table);
    }

    public void startServer() {
        Thread serverThread = new Thread(new Server(this));
        serverThread.start();
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);

        glyphLayout.setText(font, connectionStatus);
        float messageWidth = glyphLayout.width;// contains the width of the current set text
        float messageHeight = glyphLayout.height; // contains the height of the current set text
        float fontPositionX = (hostButton.getX() + hostButton.getWidth() / 2) + (hostButton.getWidth() / 2) - messageWidth / 2;
        float fontPositionY = (hostButton.getY() + hostButton.getHeight()) + messageHeight * 3;

        font.draw(batch, connectionStatus, fontPositionX, fontPositionY);
    }

    public void startGameAsServer(){
        game.setScreen(new Game(game.batch));
    }

}
