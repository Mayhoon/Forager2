package animations;

import Enums.AnimationName;
import Enums.Direction;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CustomAnimation {
    private Direction direction;
    private AnimationName animationName;
    private float timeBetweenFrames;
    private TextureRegion[] animationFrames;
    private Animation<TextureRegion> animation;
    //private

    public CustomAnimation(TextureRegion[] animationFrames) {
        timeBetweenFrames = 0.1f;
        this.animationFrames = animationFrames;
       // this.animationFrames = new Animation<TextureRegion>(timeBetweenFrames, animationFrames);
        animation = new Animation<>(timeBetweenFrames, animationFrames);
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

   // public
}
