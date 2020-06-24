package collision;

import com.badlogic.gdx.physics.box2d.*;

public class ShapeContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if (fa == null || fb == null) {
            return;
        }
        if (fa.getUserData() == null || fb.getUserData() == null) {
            return;
        }

        //When the same type of objects hit each other
        if (isTestBoxCollision(fa, fb)) {
            TutorialBox entity1 = (TutorialBox) fa.getUserData();
            TutorialBox entity2 = (TutorialBox) fb.getUserData();

            entity1.id;
            entity2.hit();

            //For every other collision between
        } else {
            TutorialBox tutorialBox = (TutorialBox) fa.getUserData();
            tutorialBox.hit();
        }
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

    //Tests if both objects are of the same type (swords clashing detection)
    private boolean isTestBoxCollision(Fixture a, Fixture b) {
        return a.getUserData() instanceof TutorialBox && b.getUserData() instanceof TutorialBox;
    }
}
