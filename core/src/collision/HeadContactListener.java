package collision;

import collision.bodyparts.Head;
import com.badlogic.gdx.physics.box2d.*;

public class HeadContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if (fa == null || fb == null) {
//            System.out.println("fixture null");
            return;
        }

        if (fa.getBody().getUserData() == null || fb.getBody().getUserData() == null) {
//            System.out.println("userData null");
            return;
        }

        //When the same type of objects hit each other
        if (isTestBoxCollision(fa, fb)) {
            System.out.println("Hier kommt er rein");
            Head entity1 = (Head) fa.getUserData();
            Head entity2 = (Head) fb.getUserData();

            entity2.hit();
        }
        //For every other collision between
        else {
            Head head = (Head) fa.getBody().getUserData();
            head.hit();
        }
    }

    //Tests if both objects are of the same type (swords clashing detection)
    private boolean isTestBoxCollision(Fixture a, Fixture b) {
        return a.getUserData() instanceof Head && b.getUserData() instanceof Head;
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

}
