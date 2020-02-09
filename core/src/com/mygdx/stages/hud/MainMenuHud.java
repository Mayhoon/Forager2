package com.mygdx.stages.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.config.Paths;
import com.mygdx.screens.MainMenuScreen;
import com.mygdx.stages.ownStage;

import java.io.IOException;

public class MainMenuHud extends ownStage {
    MainMenuScreen mainMenuScreen;
    ImageButton hostButton, joinButton;
    Table table;

    public MainMenuHud(final MainMenuScreen mainMenuScreen) {
        this.mainMenuScreen = mainMenuScreen;

        //Button to host server
        Drawable hostButtonDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(Paths.HOST_BUTTON))));
        Drawable hostButtonHoveredDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(Paths.HOST_HOVERED_BUTTON))));
        hostButton = new ImageButton(hostButtonDrawable, hostButtonHoveredDrawable);

        //button for joining existing server
        Drawable joinButtonDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(Paths.JOIN_BUTTON))));
        Drawable joinButtonHoveredDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(Paths.JOIN_HOVERED_BUTTON))));
        joinButton = new ImageButton(joinButtonDrawable, joinButtonHoveredDrawable);

        hostButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int a, int b) {
                mainMenuScreen.startServer();

                //mainMenuScreen.changeStage(this, serverHud);
                return true;
            }
        });

        joinButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int a, int b) {
                try {
                    mainMenuScreen.startClient();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //mainMenuScreen.changeStage(new ClientHud());
                return true;
            }
        });

        table = new Table();
        table.setWidth(Gdx.graphics.getWidth());
        table.setHeight(Gdx.graphics.getHeight());
        table.add(hostButton).center();
        table.row();
        table.add(joinButton).center().padTop(20);

        addActor(table);
    }
}
