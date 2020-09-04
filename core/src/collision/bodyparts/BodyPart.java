package collision.bodyparts;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import enums.AnimationName;
import enums.Direction;

public abstract class BodyPart {
    public String id;
    public Body body;
    private AnimationName animation;
    private float offsetX, offsetY;
    private AnimationName animationName;
    private int animationFrame;
    private Direction direction;

    public BodyPart(AnimationName animationName, Body body, String id, int animationFrame, Direction direction, float offsetX, float offsetY) {
        this.animationName = animationName;
        this.body = body;
        this.animationFrame = animationFrame;
        this.direction = direction;
        this.body.setActive(false);
        this.id = id;
        this.offsetX = offsetX;
        this.offsetY = offsetY;

    }

    public Body getBody() {
        return body;
    }

    public abstract void hit();

    public void updatePosition(Vector2 position, AnimationName animationName, int animationFrame, Direction direction) {
        if (this.animationName == animationName && this.animationFrame == animationFrame && this.direction == direction) {
            body.setActive(true);
            body.setTransform((position.x / 10) + offsetX, position.y + offsetY, 0);
        }else {
            body.setActive(false);
        }
    }

    public void deactivate() {
        body.setActive(false);
    }
}
