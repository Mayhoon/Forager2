package collision;

import com.badlogic.gdx.physics.box2d.*;

public class HeadContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if (fa == null || fb == null) {
            return;
        }

        if (fa.getBody().getUserData() == null || fb.getBody().getUserData() == null) {
            return;
        }

        //When the same type of objects hit each other
        if (isTestBoxCollision(fa, fb)) {
            PlayerHead entity1 = (PlayerHead) fa.getUserData();
            PlayerHead entity2 = (PlayerHead) fb.getUserData();

            entity2.hit();
        }
        //For every other collision between
        else {
            PlayerHead playerHead = (PlayerHead) fa.getBody().getUserData();
            playerHead.hit();
        }
    }

    //Tests if both objects are of the same type (swords clashing detection)
    private boolean isTestBoxCollision(Fixture a, Fixture b) {
        return a.getUserData() instanceof PlayerHead && b.getUserData() instanceof PlayerHead;
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
