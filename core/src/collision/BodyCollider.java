package collision;

import collision.bodyparts.Head;
import collision.bodyparts.Legs;
import collision.bodyparts.PlayerBody;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.gushikustudios.rube.loader.RubeSceneLoader;

public class BodyCollider {
    private Head head;
    private PlayerBody playerBody;
    private Legs legs;

    public BodyCollider(World world) {
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        com.gushikustudios.rube.loader.RubeSceneLoader rubeSceneLoader = new RubeSceneLoader();
        rubeSceneLoader.addScene()

        head = new Head(bodies.get(0));
        playerBody = new PlayerBody(bodies.get(1));
        legs = new Legs(bodies.get(3));
    }

    public void updatePositions(Vector2 position) {
        head.updatePosition(position);
        playerBody.updatePosition(position);
        legs.updatePosition(position);
    }

}
