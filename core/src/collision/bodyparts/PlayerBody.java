package collision.bodyparts;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class PlayerBody extends BodyPart{

    public PlayerBody(Body body) {
        super(body, "body");
    }

    @Override
    public void hit() {

    }

    @Override
    public void updatePosition(Vector2 position, World world) {

    }
}
