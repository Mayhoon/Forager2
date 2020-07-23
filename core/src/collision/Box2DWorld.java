package collision;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.gushikustudios.rube.RubeScene;

public class Box2DWorld {
    private World world;
    private RubeScene scene;

    public Box2DWorld() {
        world = new World(new Vector2(0, 0), false);

        //Loading world created in rube
        com.gushikustudios.rube.loader.RubeSceneLoader loader = new com.gushikustudios.rube.loader.RubeSceneLoader();
        scene = loader.addScene(Gdx.files.internal("collisions/body.json"));
        scene.getWorld().setGravity(new Vector2(0, 0));
        scene.printStats();

        //Adding the other players collisions
        // scene.addBodies(scene.getBodies());
        world = scene.getWorld();
        world.setContactListener(new CollisionListener());
    }

    public void step() {
        world.step(1 / 60f, 6, 2);
    }

    public RubeScene getScene() {
        return scene;
    }

    public World getWorld() {
        return world;
    }
}
