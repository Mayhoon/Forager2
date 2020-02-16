package com.mygdx.stages.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.config.Paths;
import com.mygdx.networking.Client;
import com.mygdx.stages.ownStage;
import org.w3c.dom.Text;
import sun.font.TrueTypeFont;

import java.awt.*;

public class ClientHud extends ownStage {
    private Table table;
    private BitmapFont font;
    private TextField textfield;

    public ClientHud() {
//        font = new BitmapFont(Gdx.files.internal(Paths.ITEM_COUNT_FONT));

        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Fipps-Regular.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 24;
        parameter.color = Color.BLACK;
        font = fontGenerator.generateFont(parameter);

//        final String FONT_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";
//        font = new TrueTypeFont(Gdx.files.internal("B8.ttf"), FONT_CHARACTERS, 12.5f, 7.5f, 1.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        font.setColor(1f, 0f, 0f, 1f);

        Gdx.input.setInputProcessor(this);

        table = new Table();
        table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Skin skin = new Skin(Gdx.files.internal("fonts/skins/uiskin.json"));
        textfield = new TextField("localhost", skin);
        textfield.setMaxLength(14);
        textfield.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (event.getKeyCode() == Input.Keys.ENTER) {
                    startClient(textfield.getText());
                }
                return super.keyDown(event, keycode);
            }
        });
        table.add(textfield).center();
        addActor(table);
    }

    public void startClient(String serverIp) {
        Thread clientThread = new Thread(new Client(serverIp));
        clientThread.start();
    }

    @Override
    public void update(SpriteBatch batch) {
        super.update(batch);

        batch.begin();
        font.draw(batch, "Moin", 200, 200);
        batch.end();
    }
}
