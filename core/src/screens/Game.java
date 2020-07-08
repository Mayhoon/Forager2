package screens;

import camera.Camera;
import collision.HeadContactListener;
import collision.TutorialBox;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.gushikustudios.rube.RubeScene;
import com.gushikustudios.rube.loader.RubeSceneLoader;
import config.Paths;
import networking.Network;
import player.Player;
import stages.gui.GameGui;
import tools.DebugLines;

public class Game extends ScreenAdapter {
    private Camera camera;
    private SpriteBatch batch;
    private Player player, opponent;
    private Texture groundTexture;
    private Sprite groundSprite;
    private GameGui gameGui;
    private DebugLines debugLines;
    private Network network;
    private Body groundBody;
    private RubeSceneLoader rubeSceneLoader;
    private RubeScene rubeScene;
    private TutorialBox playerBox, obj1, obj2;

    private Box2DDebugRenderer debugRenderer;
    private World world;
    private Array<Body> bodies;

    public Game(Network network, SpriteBatch batch) {
        this.batch = batch;
        this.network = network;

        gameGui = new GameGui(network);
        camera = new Camera();
        camera.update();

        debugLines = new DebugLines(batch);
        debugRenderer = new Box2DDebugRenderer();

        //Loading world created in rube
        RubeSceneLoader loader = new RubeSceneLoader();
        RubeScene scene = loader.addScene(Gdx.files.internal("collisions/body.json"));
//        loader.addScene(Gdx.files.internal("collisions/body.json"));

        scene.getWorld().setGravity(new Vector2(0, 0));

        //Adding the other players collisions
        scene.addBodies(scene.getBodies());
        world = scene.getWorld();
        world.setContactListener(new HeadContactListener());

        player = new Player(batch, network.player(), true, world);
        opponent = new Player(batch, network.opponent(), false, world);
        groundTexture = new Texture(Paths.GROUND);
        groundSprite = new Sprite(groundTexture);

        bodies = new Array<Body>();
        world.getBodies(bodies);

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
        player.render(delta, network.player(), world);
        System.out.println("Before changing x: " + bodies.get(0).getPosition().x);
//        opponent.render(delta, network.opponent(), world);

//        //Collisions
//        float x = network.player().position.x;
//        float y = network.player().position.y;
//        bodies.get(0).setTransform(network.player().position.x / 10 + 3.1f, network.player().position.y + 2.08f, 0);
//        bodies.get(0).setTransform(x / 10 + 3.1f, y + 1.42f, 0);
//        bodies.get(1).setTransform(x / 10 + 3.2f, y + 0.35f, 0);

//        List listA = new ArrayList();
//        listA.get(0);

        //Render gui
        gameGui.update(batch, delta);
        batch.end();

        //Render
        debugRenderer.render(world, camera.combined.scl(10));
        camera.update();
        world.step(1 / 60f, 6, 2);

        camera.update();
        network.sendData();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
