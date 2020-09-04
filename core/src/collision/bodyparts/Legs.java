package collision.bodyparts;

import enums.AnimationName;
import com.badlogic.gdx.physics.box2d.Body;
import enums.Direction;

public class Legs extends BodyPart {

    public Legs(AnimationName animationName, Body body, int animationFrame, Direction direction, float offsetX, float offsetY) {
        super(animationName, body, "Legs", animationFrame, direction, offsetX, offsetY );
        body.setUserData(this);
    }

    @Override
    public void hit() {
        System.out.println("Legs hit");
    }


}
