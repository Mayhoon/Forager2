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

public class Collider {
    Array<AnimationCollider> collider;

    public Collider(RubeScene scene) {
        //Adding the collider for each animation
        collider = new Array<AnimationCollider>();
        collider.add(new AnimationCollider(scene, 4, AnimationName.IDLE_SWORD_NOT_DRAWN));
    }

    public void updatePositions(Vector2 position, AnimationName animationName, Direction direction, int animationFrame) {
        for (AnimationCollider animationCollider : collider) {
            animationCollider.updatePositions(position, animationName, direction, animationFrame);
        }
    }

    public void deactivate() {
        for (AnimationCollider animationCollider : collider) {
            animationCollider.deactivate();
        }
    }
}
