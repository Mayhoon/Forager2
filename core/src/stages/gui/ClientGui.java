package stages.gui;

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
import config.Paths;
import game.Main;
import networking.KryoClient;
import networking.ServerClientWrapper;
import screens.Game;
import stages.customStage;
import tools.FontLoader;

import java.io.IOException;

public class ClientGui extends customStage {
    private Main game;
    private KryoClient kryoClient;
    private GlyphLayout glyphLayout;
    private BitmapFont connectionStatusFont;
    private TextField ipAddressField;
    private ImageButton connectButton;
    public String serverIp;
    private ServerClientWrapper serverClientWrapper;
    public String connectionStatus = "Ip adress of the server:";

    public ClientGui(Main game) {
        super(game.batch);
        this.game = game;

        Gdx.input.setInputProcessor(this);
        kryoClient = new KryoClient();
        glyphLayout = new GlyphLayout();
        connectionStatusFont = new FontLoader().loadFont(Paths.ITEM_COUNT_FONT, 20, Color.BLACK);

        ipAddressField();
        connectButton();

        Table table = new Table();
        table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        table.add(ipAddressField).center();
        table.add(connectButton);
        addActor(table);
    }

    private void ipAddressField() {
        Skin skin = new Skin(Gdx.files.internal("fonts/skins/uiskin.json"));
        ipAddressField = new TextField("localhost", skin);
        ipAddressField.setMaxLength(14);
        ipAddressField.setScale(2);
        ipAddressField.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (event.getKeyCode() == Input.Keys.ENTER) {
                    startClient();
                }
                return super.keyDown(event, keycode);
            }
        });
    }

    private void startClient() {
        try {
            kryoClient = new KryoClient();
            kryoClient.start(ipAddressField.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connectButton() {
        Drawable connectButtonDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(Paths.CONNECT_TO_SERVER_BUTTON))));
        Drawable connectButtonHoveredDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(Paths.CONNECT_TO_SERVER_BUTTON))));
        connectButton = new ImageButton(connectButtonDrawable, connectButtonHoveredDrawable);
        connectButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int a, int b) {
                serverIp = ipAddressField.getText();
                startClient();
                return true;
            }
        });
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        glyphLayout.setText(connectionStatusFont, connectionStatus);
        float messageWidth = glyphLayout.width; // contains the width of the current set text
        float messageHeight = glyphLayout.height; // contains the height of the current set text
        float fontPositionX = (ipAddressField.getX() + ipAddressField.getWidth() / 2) + (connectButton.getWidth() / 2) - messageWidth / 2;
        float fontPositionY = (ipAddressField.getY() + ipAddressField.getHeight()) + messageHeight * 3;
        connectionStatusFont.draw(batch, connectionStatus, fontPositionX, fontPositionY);

        if (kryoClient.running) {
            serverClientWrapper = new ServerClientWrapper(kryoClient);
            startGame();
        }
    }

    public void startGame() {
        game.setScreen(new Game(serverClientWrapper, game.batch));
    }
}
