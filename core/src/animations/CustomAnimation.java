package animations;

import Enums.AnimationState;
import Enums.Direction;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CustomAnimation {
    private Direction direction;
    private AnimationState animationName;
    private float timeBetweenFrames;
    private Animation<TextureRegion> animationFrames;

    public CustomAnimation(Animation animationFrames) {
        this.animationFrames = animationFrames;
    }

    public Animation<TextureRegion> getAnimation() {
        return animationFrames;
    }
}
