package collision;

import Enums.AnimationName;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Map;

public class AttackHitBox {
    private Map<AnimationName, Map<Integer, Vector2[][]>> attackHitBoxes;
    private Map<Integer, Vector2[][]> animationFrameCollsions;

    public AttackHitBox() {
        attackHitBoxes = new HashMap<>();
        
        //SWORD_SLASH_UP_DOWN_STANDING
        animationFrameCollsions = new HashMap<>();
        Vector2[][] frame4 = new Vector2[4][2];
        frame4[0][0] = new Vector2(5, 9);
        frame4[0][1] = new Vector2(12, 9);
        frame4[1][0] = new Vector2(14, 0);
        frame4[1][1] = new Vector2(21, 0);
        frame4[2][0] = new Vector2(13, -7);
        frame4[2][1] = new Vector2(21, -7);
        frame4[3][0] = new Vector2(-13, -18);
        frame4[3][1] = new Vector2(16, -18);

        animationFrameCollsions.put(3, frame4);

        attackHitBoxes.put(AnimationName.SWORD_SLASH_UP_DOWN_STANDING, animationFrameCollsions);
    }

    public Vector2[][] getAttackHitboxes (AnimationName animationName, int index) {
        return attackHitBoxes.get(animationName).get(index);
    }
}
