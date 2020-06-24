package screens;

import animations.CollisionChecker;
import camera.Camera;
import collision.ShapeContactListener;
import collision.TutorialBox;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.gushikustudios.rube.RubeScene;
import com.gushikustudios.rube.loader.RubeSceneLoader;
import config.Paths;
import networking.Network;
import player.Player;
import stages.gui.GameGui;
import sun.security.provider.SHA;
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
    private Body body;
    private RubeSceneLoader rubeSceneLoader;
    private RubeScene rubeScene;
    private TutorialBox playerBox, obj1, obj2;

    private Box2DDebugRenderer debugRenderer;
    private World world;

    public Game(Network network, SpriteBatch batch) {
        this.batch = batch;
        this.network = network;

        collisionChecker = new CollisionChecker(batch, network.player(), network.opponent());

        gameGui = new GameGui(network);
        camera = new Camera();
        camera.zoom -= 0.6f;
        camera.update();

        player = new Player(batch, network.player(), true);
        opponent = new Player(batch, network.opponent(), false);

        groundTexture = new Texture(Paths.GROUND);
        groundSprite = new Sprite(groundTexture);
        debugLines = new DebugLines(batch);

        initWorld();

        playerBox = new TutorialBox(world, "Player", 0, 0);
        obj1 = new TutorialBox(world, "Obj1", 15, 15);
        obj2 = new TutorialBox(world, "Obj2", 30, 15);

//        RubeSceneLoader loader = new RubeSceneLoader();
//        RubeScene scene = loader.loadScene(Gdx.files.internal("player.json"));
    }

    private void initWorld() {
//                Box2D.init();
//        world = new World(new Vector2(0, -9.8f), true);
        world = new World(new Vector2(0, 0f), true);
        world.setContactListener(new ShapeContactListener());
        debugRenderer = new Box2DDebugRenderer();

        //Player1 shape
        BodyDef playerBodyDef = new BodyDef();
        // We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        playerBodyDef.type = BodyDef.BodyType.DynamicBody;
        // Set our body's starting position in the world
        playerBodyDef.position.set(100, 300);
        // Create our body in the world using our body definition
        body = world.createBody(playerBodyDef);
        // Create a circle shape and set its radius to 6
        CircleShape circle = new CircleShape();
        circle.setRadius(6f);
        // Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f; // Make it bounce a little bit
        // Create our fixture and attach it to the body
        Fixture fixture = body.createFixture(fixtureDef);
        // Remember to dispose of any shapes after you're done with them!
        // BodyDef and FixtureDef don't need disposing, but shapes do.
        circle.dispose();


        //Opponent shape
        BodyDef opponentBodyDef = new BodyDef();
        // We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        opponentBodyDef.type = BodyDef.BodyType.DynamicBody;
        // Set our body's starting position in the world
        opponentBodyDef.position.set(50, 300);
        // Create our body in the world using our body definition
        body = world.createBody(opponentBodyDef);
        // Create a circle shape and set its radius to 6
        CircleShape circle2 = new CircleShape();
        circle2.setRadius(6f);
        // Create a fixture definition to apply our shape to
        FixtureDef fixtureDef2 = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f; // Make it bounce a little bit
        // Create our fixture and attach it to the body
        Fixture fixture2 = body.createFixture(fixtureDef);
        // Remember to dispose of any shapes after you're done with them!
        // BodyDef and FixtureDef don't need disposing, but shapes do.
        circle.dispose();


//        //GROUND
//        BodyDef groundBodyDef = new BodyDef();
//        groundBodyDef.position.set(new Vector2(0, 10));
//        // Create a body from the definition and add it to the world
//        groundBody = world.createBody(groundBodyDef);
//        // Create a polygon shape
//        PolygonShape groundBox = new PolygonShape();
//        // Set the polygon shape as a box which is twice the size of our view port and 20 high
//        // (setAsBox takes half-width and half-height as arguments)
//        groundBox.setAsBox(200, 10.0f);
//        // Create a fixture from our polygon shape and add it to our ground body
//        groundBody.createFixture(groundBox, 0.0f);
//        groundBox.dispose();
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
            System.exit(0);
        }

        //  if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
        body.setTransform(network.player().position.x, network.player().position.y, 0);
        //    }

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

        //Collision detection
        // collisionChecker.attackCollisionPoints();

        //Render gui
        gameGui.update(batch, delta);
        batch.end();

        movePlayerBox(delta);

        //Render
        debugRenderer.render(world, camera.combined);
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
            playerBox.body.setLinearVelocity(x * 350 * delta, playerBox.body.getLinearVelocity().y);
        }

        if (y != 0) {
            playerBox.body.setLinearVelocity(playerBox.body.getLinearVelocity().x, y * 350 * delta);
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
