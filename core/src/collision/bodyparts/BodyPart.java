package collision.bodyparts;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class BodyPart {
    public String id;
    public Body body;

    public BodyPart(Body body, String id) {
        this.body = body;
        this.id = id;
    }

    public Body getBody() {
        return body;
    }

    public abstract void hit();
    public abstract void updatePosition(Vector2 position);

}
