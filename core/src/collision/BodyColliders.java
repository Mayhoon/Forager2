package collision;

import enums.AnimationName;
import enums.Direction;
import collision.bodyparts.BodyPart;
import collision.bodyparts.Head;
import collision.bodyparts.Legs;
import collision.bodyparts.PlayerBody;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.gushikustudios.rube.RubeScene;

import java.util.ArrayList;
import java.util.List;

public class BodyColliders {
    private Head head;
    private PlayerBody playerBody;
    private Legs legs;
    private List bodyParts;

    public BodyColliders(RubeScene scene) {

        //Get the body via its name which has to match the EnumName
        Array<Body> idleSwordNotDrawn_left = scene.getNamed(Body.class, AnimationName.IDLE_SWORD_NOT_DRAWN + "_" + Direction.LEFT);
        Array<Body> idleSwordNotDrawn_right = scene.getNamed(Body.class, AnimationName.IDLE_SWORD_NOT_DRAWN + "_" + Direction.RIGHT);

        Array<Body> spin_left = scene.getNamed(Body.class, AnimationName.SWORD_SLASH_SPIN + "_" + Direction.LEFT);
        Array<Body> spin_right = scene.getNamed(Body.class, AnimationName.SWORD_SLASH_SPIN + "_" + Direction.RIGHT);
        spin_left.get(0).setTransform(new Vector2(20, 0), 0);
        spin_left.get(0).setActive(true);

        //List of all bodys/ colliders
        bodyParts = new ArrayList();
//        bodyParts.add(new Head(idleSwordNotDrawn_left.get(1), AnimationName.IDLE_SWORD_NOT_DRAWN, 3.3f, 2.08f));
//        bodyParts.add(new PlayerBody(idleSwordNotDrawn_left.get(2), AnimationName.IDLE_SWORD_NOT_DRAWN, 3.4f, 1.42f));
//        bodyParts.add(new Legs(idleSwordNotDrawn_left.get(0), AnimationName.IDLE_SWORD_NOT_DRAWN, 3.2f, 0.38f));

        bodyParts.add(new Head(idleSwordNotDrawn_right.get(2), AnimationName.IDLE_SWORD_NOT_DRAWN, 3.1f, 2.08f));
        bodyParts.add(new PlayerBody(idleSwordNotDrawn_right.get(1), AnimationName.IDLE_SWORD_NOT_DRAWN, 3.1f, 1.42f));
        bodyParts.add(new Legs(idleSwordNotDrawn_right.get(0), AnimationName.IDLE_SWORD_NOT_DRAWN, 3.2f, 0.38f));

//        bodyParts.add(new Head(spin_left.get(0), AnimationName.IDLE_SWORD_NOT_DRAWN, 3.1f, 2.08f));
//        bodyParts.add(new PlayerBody(spin_right.get(0), AnimationName.IDLE_SWORD_NOT_DRAWN, 3.1f, 1.42f));

    }

    public void updatePositions(Vector2 position, AnimationName animationName) {
        for (int i = 0; i < bodyParts.size(); i++) {
            ((BodyPart) bodyParts.get(i)).updatePosition(position, animationName);
        }
    }

//    public void setActive() {
//        for (int i = 0; i < bodyParts.size(); i++) {
//            ((BodyPart) bodyParts.get(i)).setActive();
//        }
//    }
}
