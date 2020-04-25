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

    public CustomAnimation(TextureRegion[] frames) {
        timeBetweenFrames = 0.1f;
        animation = new Animation<>(timeBetweenFrames, frames);
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

}
