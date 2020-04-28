package collision;

import Enums.AnimationName;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Map;

public class AttackHitBox {
    private Map<AnimationName, Map<Integer, Vector2[]>> attackHitBoxes;
    private Map<Integer, Vector2[]> frames;

    public AttackHitBox() {
        attackHitBoxes = new HashMap<AnimationName, Map<Integer, Vector2[]>>();
        frames = new HashMap<Integer, Vector2[]>();

        sword_slash_up_down();
        sword_slash_spin();
    }

    public Vector2[] getAttackHitboxes(AnimationName animationName, int index) {
        return attackHitBoxes.get(animationName).get(index);
    }

    private void sword_slash_up_down() {
        frames = new HashMap<Integer, Vector2[]>();
        Vector2[] vec = {
                v(0, 0), v(0, 10),
                v(7, 10), v(7, 20),
                v(10, 10), v(20, 20)};
        frames.put(3, vec);
        attackHitBoxes.put(AnimationName.SWORD_SLASH_UP_DOWN_STANDING, frames);
    }

    private void sword_slash_spin() {
        frames = new HashMap<Integer, Vector2[]>();
        attackHitBoxes.put(AnimationName.SWORD_SLASH_SPIN, frames);
    }

    private Vector2 v(int x, int y) {
        return new Vector2(x, y);
    }
}
