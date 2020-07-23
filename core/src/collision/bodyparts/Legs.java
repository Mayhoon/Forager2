package collision.bodyparts;

import enums.AnimationName;
import com.badlogic.gdx.physics.box2d.Body;

public class Legs extends BodyPart {

    public Legs(Body body, AnimationName animation, float offsetX, float offsetY) {
        super(body, "Legs", animation, offsetX, offsetY );
        body.setUserData(this);
    }

    @Override
    public void hit() {
        System.out.println("Legs hit");
    }


}
