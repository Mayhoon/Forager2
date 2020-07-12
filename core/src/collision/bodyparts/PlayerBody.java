package collision.bodyparts;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class PlayerBody extends BodyPart{

    public PlayerBody(Body body) {
        super(body, "body");
        body.setUserData(this);
    }

    @Override
    public void hit() {
        System.out.println("[ Body hit ]");
    }

    @Override
    public void updatePosition(Vector2 position) {
        body.setTransform(position.x / 10 + 3.1f, position.y + 1.42f, 0);
    }
}
