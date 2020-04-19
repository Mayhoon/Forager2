package player;

import Enums.AnimationState;
import animations.AnimationLoader;
import animations.CustomAnimation;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import config.Paths;

import java.util.HashMap;
import java.util.Map;

public class PlayerAnimations {
    private AnimationLoader animationLoader;
    private Map<AnimationState, CustomAnimation> map;

    public PlayerAnimations() {
        animationLoader = new AnimationLoader(Paths.PLAYER_ANIMATION, 8, 10);
        map = new HashMap<>();
        map.put(AnimationState.IDLE_SWORD_NOT_DRAWN, new CustomAnimation(animationLoader.load(0, 0, 4)));
        map.put(AnimationState.IDLE_SWORD_DRAWN, new CustomAnimation(animationLoader.load(0, 5, 8)));
        map.put(AnimationState.DRAW_SWORD, new CustomAnimation(animationLoader.load(3, 0, 4)));
        map.put(AnimationState.SWORD_SLASH_UP_DOWN_STANDING, new CustomAnimation(animationLoader.load(1, 0, 6)));
        map.put(AnimationState.SWORD_SLASH_SPIN, new CustomAnimation(animationLoader.load(2, 0, 6)));
    }

    public Animation<TextureRegion> get (AnimationState name) {
        return map.get(name).getAnimation();
    }
}
