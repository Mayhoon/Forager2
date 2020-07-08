package collision.bodyparts;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Head extends BodyPart {

    public Head(Body body) {
        super(body, "Head");
    }

    @Override
    public void hit() {
        System.out.println("["+ id +"] got hit");
    }

    @Override
    public void updatePosition(Vector2 position, World world) {
//        System.out.println("Update position" + position.x);
//        System.out.println("Before moving:" + getBody().getPosition().x);
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        bodies.get(0).setTransform(position.x / 10 + 3.1f, position.y + 2.08f, 0);
//            body.setTransform(position.x / 10 + 3.1f, position.y + 2.08f, 0);
//        System.out.println("After moving:" + getBody().getPosition().x);
    }


}
