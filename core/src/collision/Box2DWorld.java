package collision;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.gushikustudios.rube.RubeScene;
import com.gushikustudios.rube.loader.RubeSceneLoader;

public class Box2DWorld {
    private World world;

    public Box2DWorld() {
        world = new World(new Vector2(0, 0), false);

        //Loading world created in rube
        RubeSceneLoader loader = new com.gushikustudios.rube.loader.RubeSceneLoader();
        RubeScene scene = loader.addScene(Gdx.files.internal("collisions/body.json"));

        Array<Body> b = scene.getNamed(Body.class, "7_swing");
        b.get(0).setTransform(new Vector2(0, 0), 0);

        scene.getWorld().setGravity(new Vector2(0, 0));
        scene.printStats();

        //Adding the other players collisions
        // scene.addBodies(scene.getBodies());
        world = scene.getWorld();
        world.setContactListener(new CollisionListener());
    }

    public World getWorld() {
        return world;
    }


    public void step() {
        world.step(1 / 60f, 6, 2);
    }
}
