package collision.bodyparts;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class Legs extends BodyPart{

    public Legs(Body body) {
        super(body, "Legs");
    }

    @Override
    public void hit() {
        System.out.println("Legs hit");
    }

    @Override
    public void updatePosition(Vector2 position) {
        body.setTransform(position.x / 10 + 3.2f, position.y + 0.38f, 0);
    }

}
