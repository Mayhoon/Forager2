package collision.bodyparts;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Head extends BodyPart {

    public Head(Body body) {
        super(body, "Head");
        body.setUserData(this);
    }

    @Override
    public void hit() {
        System.out.println("[" + id + "] at position " + body.getPosition().x + "got hit");
    }

    @Override
    public void updatePosition(Vector2 position) {
        body.setTransform(position.x / 10 + 3.1f, position.y + 2.08f, 0);
    }


}
