package collision.bodyparts;

import enums.AnimationName;
import com.badlogic.gdx.physics.box2d.Body;

public class Sword extends BodyPart {

    public Sword(Body body, String id, AnimationName animation, float offsetX, float offsetY) {
        super(body, id, animation, offsetX, offsetY);
        body.setUserData(this);
    }

    @Override
    public void hit() {

    }
}
