package com.mygdx.stages.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.config.Resources;
import com.mygdx.game.Main;
import com.mygdx.networking.Client;
import com.mygdx.screens.Game;
import com.mygdx.stages.customStage;
import com.mygdx.tools.FontLoader;

public class ClientHud extends customStage {
    private BitmapFont connectionStatusFont;
    private TextField ipAdressField;
    private ImageButton connectButton;
    private GlyphLayout glyphLayout;
    public String serverIp;
    private Main game;
    private Thread clientThread;
    private Client client;

    public String connectionStatus = "Ip adress of the server:";

    public ClientHud(Main game) {
        super(game.batch);
        this.game = game;
        client = new Client(this);

        /*  determines the length of the connection message
        used for positioning */
        glyphLayout = new GlyphLayout();

        Gdx.input.setInputProcessor(this);
        connectionStatusFont = new FontLoader().loadFont(Resources.ITEM_COUNT_FONT, 20, Color.BLACK);
        Table table = new Table();
        table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Skin skin = new Skin(Gdx.files.internal("fonts/skins/uiskin.json"));
        ipAdressField = new TextField("localhost", skin);
        ipAdressField.setMaxLength(14);
        ipAdressField.setScale(2);
        ipAdressField.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (event.getKeyCode() == Input.Keys.ENTER) {
                    connectToIpAdress();
                }
                return super.keyDown(event, keycode);
            }
        });
        table.add(ipAdressField).center();

        //button for joining existing server
        Drawable connectButtonDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(Resources.CONNECT_TO_SERVER_BUTTON))));
        Drawable connectButtonHoveredDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(Resources.CONNECT_TO_SERVER_BUTTON))));
        connectButton = new ImageButton(connectButtonDrawable, connectButtonHoveredDrawable);
        connectButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int a, int b) {
                connectToIpAdress();
                return true;
            }
        });
        table.add(connectButton);
        addActor(table);
    }

    private void connectToIpAdress() {
        connectionStatus += ipAdressField.getText();
        serverIp = ipAdressField.getText();
        clientThread = new Thread();
        clientThread.start();
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);

        glyphLayout.setText(connectionStatusFont, connectionStatus);
        float messageWidth = glyphLayout.width;// contains the width of the current set text
        float messageHeight = glyphLayout.height; // contains the height of the current set text
        float fontPositionX = (ipAdressField.getX() + ipAdressField.getWidth() / 2) + (connectButton.getWidth() / 2) - messageWidth / 2;
        float fontPositionY = (ipAdressField.getY() + ipAdressField.getHeight()) + messageHeight * 3;

        connectionStatusFont.draw(batch, connectionStatus, fontPositionX, fontPositionY);

        if (client.running) {
            startGameAsClient();
        }

    }

    public void startGameAsClient() {
        System.out.println("STARTING THE GAME");
        game.setScreen(new Game(game.batch));
    }
}
