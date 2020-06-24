package collision;

import com.badlogic.gdx.physics.box2d.*;

public class TutorialBox {

    public Body body;
    public String id;
    private World world;

    public TutorialBox(World world, String id, float x, float y) {
        this.id = id;
        createBoxBody(world, x, y);
    }

    private void createBoxBody(World world, float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(20, 20);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(10, 10);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;

        this.body = world.createBody(bodyDef);
        this.body.createFixture(fixtureDef).setUserData(this); //Enabling to access attributes speicifc to this object
    }

    public void hit() {
        System.out.println("[" + id + "] got hit.");
    }

}
