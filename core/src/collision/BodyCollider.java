package collision;

import collision.bodyparts.Head;
import collision.bodyparts.Legs;
import collision.bodyparts.PlayerBody;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class BodyCollider {
    private Head head;
    private PlayerBody playerBody;
    private Legs legs;

    public BodyCollider(World world) {
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        head = new Head(bodies.get(0));
        playerBody = new PlayerBody(bodies.get(1));
        legs = new Legs(bodies.get(2));

        //Collisions
//        float x = network.player().position.x;
//        float y = network.player().position.y;
//        bodies.get(2).setTransform(x / 10 + 3.1f, y + 2.08f, 0);
//        bodies.get(3).setTransform(x / 10 + 3.1f, y + 1.42f, 0);
//        bodies.get(1).setTransform(x / 10 + 3.2f, y + 0.35f, 0);
    }

    public void updatePositions(Vector2 position, World world) {

        head.updatePosition(position, world);
    }

}
