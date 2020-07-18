package collision.bodyparts;

import Enums.AnimationName;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Head extends BodyPart {

    public Head(Body body, AnimationName animation, float offsetX, float offsetY) {
        super(body, "Head", animation, offsetX, offsetY);
        body.setUserData(this);
    }

    @Override
    public void hit() {
        System.out.println("[" + id + "] at position " + body.getPosition().x + "got hit");
    }
}
