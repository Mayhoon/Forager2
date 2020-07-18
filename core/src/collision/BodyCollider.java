package collision;

import Enums.AnimationName;
import collision.bodyparts.BodyPart;
import collision.bodyparts.Head;
import collision.bodyparts.Legs;
import collision.bodyparts.PlayerBody;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

public class BodyCollider {
    private Head head;
    private PlayerBody playerBody;
    private Legs legs;
    private List bodyParts;

    public BodyCollider(World world) {
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        bodyParts = new ArrayList();
//        bodyParts.add(new Head(bodies.get(0), AnimationName.IDLE_SWORD_NOT_DRAWN, 3.1f, 2.08f));
//        bodyParts.add(new PlayerBody(bodies.get(1), AnimationName.IDLE_SWORD_NOT_DRAWN, 3.1f, 1.42f));
//        bodyParts.add(new Legs(bodies.get(2), AnimationName.IDLE_SWORD_NOT_DRAWN, 3.2f, 0.38f));

        bodyParts.add(new Head(bodies.get(6), AnimationName.IDLE_SWORD_NOT_DRAWN, 0, 0));
//        bodyParts.add(new PlayerBody(bodies.get(1), AnimationName.IDLE_SWORD_NOT_DRAWN, 0, 0));
//        bodyParts.add(new Legs(bodies.get(2), AnimationName.IDLE_SWORD_NOT_DRAWN, 0, 0));
    }

    public void updatePositions(Vector2 position, AnimationName animationName) {
        for (int i = 0; i < bodyParts.size(); i++) {
            BodyPart bp = ((BodyPart) bodyParts.get(i));
            bp.updatePosition(position, animationName);
        }
    }

    public void setActive() {
        for (int i = 0; i < bodyParts.size(); i++) {
            ((BodyPart) bodyParts.get(i)).setActive();
        }
    }
}
