package collision.bodyparts;

import com.badlogic.gdx.physics.box2d.Body;
import enums.AnimationName;
import enums.Direction;

public class PlayerBody extends BodyPart {

    public PlayerBody(AnimationName animationName, Body body, int animationFrame, Direction direction, float offsetX, float offsetY) {
        super(animationName, body, "Body", animationFrame, direction, offsetX, offsetY);
        body.setUserData(this);
    }

    @Override
    public void hit() {
        System.out.println("[ Body hit ]");
    }
}
