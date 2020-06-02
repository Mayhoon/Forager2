package collision;

import Enums.AnimationName;
import com.badlogic.gdx.math.Vector2;
import config.Paths;

import java.util.Map;

public abstract class AnimationHitBox {
    public Vector2 v(int x, int y) {
        return new Vector2(x, Paths.PLAYER_KEYFRAME_HEIGHT - y);
    }
}
