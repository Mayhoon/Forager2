package collision;

import collision.bodyparts.BodyPart;
import collision.bodyparts.Head;
import collision.bodyparts.Legs;
import collision.bodyparts.PlayerBody;
import com.badlogic.gdx.physics.box2d.*;

public class CollisionListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if (fixtureA == null || fixtureB == null) {
            System.out.println("fixture null");
            return;
        }

        if(bodyHead(fixtureA, fixtureB)) {
            System.out.println("123");
        }

        //When the same type of objects hit each other
        if (areBothHeads(fixtureA, fixtureB)) {
            System.out.println("Hier kommt er rein");
            Head entity1 = (Head) fixtureA.getUserData();
            Head entity2 = (Head) fixtureB.getUserData();
            entity2.hit();
        }

        //For every other collision between
        if (areBothHeads(fixtureA, fixtureB)) {
            Head head = (Head) fixtureA.getBody().getUserData();
            head.hit();
        }

        if(isHeadLegCollision(fixtureA, fixtureB)) {
            System.out.println("Head and leg collsion");
        }
    }

    //Tests if both objects are of the same type (swords clashing detection)
    private boolean areBothHeads(Fixture fixtureA, Fixture fixtureB) {
        return fixtureA.getBody().getUserData() instanceof Head && fixtureB.getBody().getUserData() instanceof Head;
    }

    private boolean bodyHead(Fixture fixtureA, Fixture fixtureB) {
        return fixtureA.getBody().getUserData() instanceof PlayerBody && fixtureB.getBody().getUserData() instanceof Head;
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    private void checkForSwordCollision() {

    }

    private boolean isHeadLegCollision(Fixture fixtureA, Fixture fixtureB) {
        return fixtureA.getBody().getUserData() instanceof Head && fixtureB.getBody().getUserData() instanceof Legs;
    }
}
