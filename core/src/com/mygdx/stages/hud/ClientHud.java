package com.mygdx.stages.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.config.Paths;
import com.mygdx.networking.Client;
import com.mygdx.stages.customStage;
import com.mygdx.tools.FontLoader;

import static jdk.internal.vm.PostVMInitHook.run;

public class ClientHud extends customStage {
    private Table table;
    private BitmapFont font;
    private TextField textfield;

    private boolean renderConnectionMessage;
    public String connectionStatus = "Trying to connect to ";

    public ClientHud(SpriteBatch batch) {
        super(batch);

        renderConnectionMessage = false;

        Gdx.input.setInputProcessor(this);
        font = new FontLoader().loadFont(Paths.ITEM_COUNT_FONT, 24, Color.BLACK);
        table = new Table();
        table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Skin skin = new Skin(Gdx.files.internal("fonts/skins/uiskin.json"));
        textfield = new TextField("localhost", skin);
        textfield.setMaxLength(14);
        textfield.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (event.getKeyCode() == Input.Keys.ENTER) {
                    renderConnectionMessage = true;
                    connectionStatus += textfield.getText();
                    startClient(textfield.getText(), this);
                }
                return super.keyDown(event, keycode);
            }
        });
        table.add(textfield).center();
        addActor(table);
    }

    private void startClient(String serverIp, ClientHud clientHud) {
        Thread clientThread = new Thread(new Client(serverIp, clientHud));
        clientThread.start();
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        if (renderConnectionMessage) {
            font.draw(batch, connectionStatus + textfield.getText(), Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 3);
        }
    }
}
