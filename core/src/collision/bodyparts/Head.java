package collision.bodyparts;

import enums.AnimationName;
import com.badlogic.gdx.physics.box2d.Body;
import enums.Direction;

public class Head extends BodyPart {

    public Head(AnimationName animationName, Body body, int frameNumber, Direction direction, float offsetX, float offsetY) {
        super(animationName, body, "Head", frameNumber, direction, offsetX, offsetY);
        body.setUserData(this);
    }

    @Override
    public void hit() {
        System.out.println("[" + id + "] at position " + body.getPosition().x + "got hit");
    }
}
