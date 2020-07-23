package collision;

import collision.bodyparts.BodyPart;
import com.badlogic.gdx.math.Vector2;
import enums.AnimationName;

public class AnimationCollision {

    public void updatePositions(Vector2 position, AnimationName animationName) {
        for (int i = 0; i < bodyParts.size(); i++) {
            ((BodyPart) bodyParts.get(i)).updatePosition(position, animationName);
        }
    }
}
