package player;

import enums.AnimationName;
import animations.AnimationLoader;
import animations.CustomAnimation;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import config.Paths;

import java.util.HashMap;
import java.util.Map;

public class PlayerAnimations {
    private AnimationLoader animationLoader;
    private Map<AnimationName, CustomAnimation> map;

    public PlayerAnimations() {
        animationLoader = new AnimationLoader(Paths.PLAYER_ANIMATION, 8, 10);
        map = new HashMap<>();
        map.put(AnimationName.IDLE_SWORD_NOT_DRAWN, new CustomAnimation(animationLoader.load(0, 0, 4)));
        map.put(AnimationName.IDLE_SWORD_DRAWN, new CustomAnimation(animationLoader.load(0, 5, 8)));
        map.put(AnimationName.DRAW_SWORD, new CustomAnimation(animationLoader.load(3, 0, 4)));
        map.put(AnimationName.SWORD_SLASH_UP_DOWN, new CustomAnimation(animationLoader.load(1, 0, 6)));
        map.put(AnimationName.SWORD_SLASH_SPIN, new CustomAnimation(animationLoader.load(2, 0, 6)));
    }

    public Animation<TextureRegion> get (AnimationName animationName) {
        return map.get(animationName).getAnimation();
    }
}
