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
import com.mygdx.config.Resources;
import com.mygdx.screens.Startmenu;
import com.mygdx.stages.customStage;

public class StartmenuHud extends customStage {
    Startmenu startmenu;
    ImageButton hostButton, joinButton;
    Table table;

    public StartmenuHud(Startmenu startmenu) {
        super(startmenu.game.batch);
        this.startmenu = startmenu;
        Gdx.input.setInputProcessor(this);

        //Button to host server
        Drawable hostButtonDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(Resources.HOST_BUTTON))));
        Drawable hostButtonHoveredDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(Resources.HOST_HOVERED_BUTTON))));
        hostButton = new ImageButton(hostButtonDrawable, hostButtonHoveredDrawable);

        //Button for joining existing server
        Drawable joinButtonDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(Resources.JOIN_BUTTON))));
        Drawable joinButtonHoveredDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(Resources.JOIN_HOVERED_BUTTON))));
        joinButton = new ImageButton(joinButtonDrawable, joinButtonHoveredDrawable);

        hostButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int a, int b) {
                hostButtonClicked();
                return true;
            }
        });

        joinButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int a, int b) {
                joinButtonClicked();
                return true;
            }
        });

        //Hud
        table = new Table();
        table.setWidth(Gdx.graphics.getWidth());
        table.setHeight(Gdx.graphics.getHeight());
        table.add(hostButton).center();
        table.row();
        table.add(joinButton).center().padTop(20);
        addActor(table);
    }

    private void hostButtonClicked() {
        startmenu.stageManager.changeStage(new ServerHud(startmenu.game));
    }

    private void joinButtonClicked() {
        startmenu.stageManager.changeStage(new ClientHud(startmenu.game));
    }
}
