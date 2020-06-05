package screens;

import Enums.Entity;
import camera.Camera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import config.Paths;
import networking.Network;
import player.Player;
import stages.gui.GameGui;
import tools.DebugLines;
import tools.FpsDisplay;

public class Game extends ScreenAdapter {
    private Camera camera;
    private SpriteBatch batch;
    private Player player, opponent;
    private Texture groundTexture;
    private Sprite groundSprite;
    private FpsDisplay fpsDisplay;
    private GameGui gameGui;
    private DebugLines debugLines;
    private Network network;

    public Game(Network network, SpriteBatch batch) {
        this.batch = batch;
        this.network = network;

        gameGui = new GameGui(batch, network);
        camera = new Camera();
        camera.zoom -= 0.6f;
        camera.update();

        player = new Player(batch, network, Entity.Player);
        opponent = new Player(batch, network, Entity.Opponent);
        groundTexture = new Texture(Paths.GROUND);
        groundSprite = new Sprite(groundTexture);
        debugLines = new DebugLines(batch);
    }


    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
            System.exit(0);
        }

        Gdx.gl.glClearColor(255 / 255, 255 / 255, 255 / 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Render world
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        debugLines.render();
        batch.draw(groundSprite, 0, -17);

        player.update(delta);
        player.render(delta);

        opponent.render(delta);


        //Render gui
        gameGui.update(delta);

        batch.end();
        camera.update();

        network.sendData();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
