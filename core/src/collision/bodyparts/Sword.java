package collision.bodyparts;

import com.badlogic.gdx.physics.box2d.Body;
import enums.AnimationName;
import enums.Direction;

public class Sword extends BodyPart {

    public Sword(AnimationName animationName, Body body, String id, int animationFrame, Direction direction, float offsetX, float offsetY) {
        super(animationName, body, id, animationFrame, direction, offsetX, offsetY);
        body.setUserData(this);
    }

    @Override
    public void hit() {

    }
}
