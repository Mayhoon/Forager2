package screens;

import camera.Camera;
import collision.Box2DWorld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import config.Paths;
import networking.Network;
import player.Player;
import stages.gui.GameGui;
import tools.DebugLines;

public class Game extends ScreenAdapter {
    private Camera camera;
    private SpriteBatch batch;
    private Player player, opponent;
    private Network network;
    private Body groundBody;
    private Texture groundTexture;
    private Sprite groundSprite;
    private GameGui gameGui;
    private DebugLines debugLines;
    private Box2DDebugRenderer debugRenderer;
    private Box2DWorld box2DWorld;

    public Game(Network network, SpriteBatch batch) {
        this.batch = batch;
        this.network = network;

        gameGui = new GameGui(network);
        camera = new Camera();
        camera.update();

        debugLines = new DebugLines(batch);
        debugRenderer = new Box2DDebugRenderer();
        box2DWorld = new Box2DWorld();

        player = new Player(batch, network.player(), true, box2DWorld.getScene());
//        opponent = new Player(batch, network.opponent(), false, box2DWorld.getWorld());
        groundTexture = new Texture(Paths.GROUND);
        groundSprite = new Sprite(groundTexture);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
            System.exit(0);
        }

        Gdx.gl.glClearColor(100 / 255f, 20 / 255f, 100 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Render world
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        debugLines.render();
        batch.draw(groundSprite, 0, -17);

        //Character
        player.update(delta);
        player.render(delta, network.player());
//        opponent.render(delta, network.opponent());

        //Render gui
        gameGui.update(batch, delta);
        batch.end();

        //Render
        debugRenderer.render(box2DWorld.getWorld(), camera.combined.scl(10));
        camera.update();
        box2DWorld.step();

        camera.update();
        network.sendData();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
