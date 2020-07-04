package screens;

import animations.CollisionChecker;
import camera.Camera;
import collision.HeadContactListener;
import collision.PlayerHead;
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
    private CollisionChecker collisionChecker;
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

        player = new Player(batch, network.player(), true);
        opponent = new Player(batch, network.opponent(), false);

        groundTexture = new Texture(Paths.GROUND);
        groundSprite = new Sprite(groundTexture);
        debugLines = new DebugLines(batch);

        //Box2D world
//        world = new World(new Vector2(0, 0f), true);
//        world.setContactListener(new ShapeContactListener());
        debugRenderer = new Box2DDebugRenderer();

//        playerBox = new TutorialBox(world, "Player", 0, 0);
//        obj1 = new TutorialBox(world, "Obj1", 15, 15);
//        obj2 = new TutorialBox(world, "Obj2", 30, 15);


        //Loading world created in rube
        RubeSceneLoader loader = new RubeSceneLoader();
        RubeScene scene = loader.loadScene(Gdx.files.internal("double.json"));
        scene.getWorld().setGravity(new Vector2(0, 0));

//        scene.addBodies();
        world = scene.getWorld();
        world.setContactListener(new HeadContactListener());

        bodies = new Array<Body>();
        world.getBodies(bodies);
        System.out.println("Amount of bodies in the scene: " + bodies.size);


        PlayerHead head2 = new PlayerHead(bodies.get(1), "Head2");
        PlayerHead head1 = new PlayerHead(bodies.get(0), "Head1");
        bodies.get(0).setUserData(new PlayerHead(bodies.get(0),"123"));

        PlayerHead obj =  (PlayerHead) bodies.get(0).getUserData();
        obj.hit();
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
        opponent.render(delta, network.opponent());

        //Render gui
        gameGui.update(batch, delta);
        batch.end();

        movePlayerBox(delta);

        //Render
        debugRenderer.render(world, camera.combined.scl(10));
        camera.update();
        world.step(1 / 60f, 6, 2);

        camera.update();
        network.sendData();
    }

    private void movePlayerBox(float delta) {
        float x = 0, y = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            x += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            x -= 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            y += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            y -= 1;
        }

        if (x != 0) {
             bodies.get(0).setLinearVelocity(x * 350 * delta, bodies.get(0).getLinearVelocity().y);
//            bodies.get(0).setTransform(20, 0, 0);
//            System.out.println(bodies.get(0).getPosition().x);
        }

        if (y != 0) {
            //playerBox.body.setLinearVelocity(playerBox.body.getLinearVelocity().x, y * 350 * delta);
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
