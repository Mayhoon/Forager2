package collision;

import com.badlogic.gdx.math.Vector2;
import com.gushikustudios.rube.RubeScene;
import enums.AnimationName;
import enums.Direction;

import java.util.ArrayList;
import java.util.List;

public class AnimationCollider {
    private List bodyPartCollider;
    private AnimationName animationName;
    private ArrayList<SingleAnimationFrameCollider> collider;

    public AnimationCollider(RubeScene scene, int amountOfFrames, AnimationName animationName) {
        this.animationName = animationName;
        collider = new ArrayList<SingleAnimationFrameCollider>();

        /*
        Adding all single frame animations facing both left and right.
         */
        for (int i = 0; i < amountOfFrames; i++) {
            collider.add(new SingleAnimationFrameCollider(scene, animationName, Direction.LEFT, i));
            collider.add(new SingleAnimationFrameCollider(scene, animationName, Direction.RIGHT, i));
        }
    }

    public void updatePositions(Vector2 position, AnimationName animationName, Direction direction, int frameIndex) {
            for (SingleAnimationFrameCollider singleAnimationFrameCollider : collider) {
                singleAnimationFrameCollider.updatePosition(position, animationName, frameIndex, direction);
            }
    }

    public void deactivate() {
        for (SingleAnimationFrameCollider singleAnimationFrameCollider : collider) {
            singleAnimationFrameCollider.deactivate();
        }
    }
}
