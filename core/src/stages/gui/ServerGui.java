package stages.gui;

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
import config.Paths;
import game.Main;
import networking.Network;
import networking.NetworkType;
import screens.Game;
import stages.customStage;
import tools.FontLoader;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerGui extends customStage {
    private Main game;
    public String serverIp;
    public String connectionStatus;
    private GlyphLayout glyphLayout;
    private BitmapFont font;
    private ImageButton hostButton;

    public ServerGui(Main game) {
        super(game.batch);
        this.game = game;
        Gdx.input.setInputProcessor(this);

        try {
            serverIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        connectionStatus = "";
        glyphLayout = new GlyphLayout();
        FontLoader fontLoader = new FontLoader();
        font = fontLoader.loadFont(Paths.ITEM_COUNT_FONT, 20, Color.BLACK);

        hostButton();

        Table table = new Table();
        table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        table.add(hostButton).center();
        addActor(table);
    }

    private void hostButton() {
        Drawable hostButtonDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(Paths.HOST_BUTTON))));
        Drawable hostButtonHoveredDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(Paths.HOST_HOVERED_BUTTON))));
        hostButton = new ImageButton(hostButtonDrawable, hostButtonHoveredDrawable);
        hostButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int a, int b) {
                startGame();
                return true;
            }
        });
    }

    public void startGame() {
        hostButton.getListeners().clear();
        game.setScreen(new Game(new Network(NetworkType.HOST), game.batch));
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        renderIp(batch);
        renderInput(batch);
    }

    private void renderIp(SpriteBatch batch) {
        glyphLayout.setText(font, serverIp);
        float serverIpWidth = glyphLayout.width;// contains the width of the current set text
        float serverIpHeight = glyphLayout.height; // contains the height of the current set text
        float serverIpX = (hostButton.getX() + hostButton.getWidth() / 2) - serverIpWidth / 2;
        float serverIpY = (hostButton.getY() + hostButton.getHeight()) + serverIpHeight * 6;
        font.draw(batch, serverIp, serverIpX, serverIpY);
    }

    private void renderInput(SpriteBatch batch) {
        glyphLayout.setText(font, connectionStatus);
        float messageWidth = glyphLayout.width; // contains the width of the current set text
        float messageHeight = glyphLayout.height; // contains the height of the current set text
        float fontPositionX = (hostButton.getX() + hostButton.getWidth() / 2) - messageWidth / 2;
        float fontPositionY = (hostButton.getY() + hostButton.getHeight()) + messageHeight * 3;
        font.draw(batch, connectionStatus, fontPositionX, fontPositionY);
    }

    @Override
    public void dispose() {
        super.dispose();
        hostButton.remove();
    }
}
