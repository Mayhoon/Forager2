package collision;

import Enums.AnimationName;
import Enums.Direction;
import com.badlogic.gdx.math.Vector2;
import config.Paths;
import networking.NetworkData;

import java.util.HashMap;
import java.util.Map;

public class Collisions {
    private Map<AnimationName, Map<Integer, Vector2[]>> attackHitBoxes;
    private Map<Integer, Vector2[]> attackFrames;

    private Map<AnimationName, Map<Integer, Vector2[]>> bodyHitBoxes;
    private Map<Integer, Vector2[]> bodyFrames;

    public Collisions() {
        attackHitBoxes = new HashMap<AnimationName, Map<Integer, Vector2[]>>();
        attackFrames = new HashMap<Integer, Vector2[]>();

        bodyHitBoxes = new HashMap<AnimationName, Map<Integer, Vector2[]>>();
        bodyFrames = new HashMap<Integer, Vector2[]>();

        sword_slash_up_down();
        sword_slash_spin();
    }

    public Vector2[] getAttackHitboxes(AnimationName animationName, int index) {
        return attackHitBoxes.get(animationName).get(index);
    }

    public Vector2[] getBodyHitboxes(AnimationName animationName, int index) {
        return bodyHitBoxes.get(animationName).get(index);
    }

    //Return the world coordinate for either the player or the opponent
    public Vector2 attackHitPoint(int index, NetworkData data) {
        Vector2 attackHitPoint = getAttackHitboxes(data.animation, data.keyFrameIndex)[index];

        if (data.direction.equals(Direction.LEFT)) {
            return new Vector2((
                    data.position.x + 64) - attackHitPoint.x,
                    attackHitPoint.y + data.position.y);
        } else if (data.direction.equals(Direction.RIGHT)) {
            return new Vector2(
                    data.position.x + attackHitPoint.x,
                    data.position.y + attackHitPoint.y);
        }
        return null;
    }

    public Vector2 bodyHitPoint(int index, NetworkData data) {
        Vector2 bodyHitPoint = getBodyHitboxes(data.animation, data.keyFrameIndex)[index];

        if (data.direction.equals(Direction.LEFT)) {
            return new Vector2((
                    data.position.x + 64) - bodyHitPoint.x,
                    bodyHitPoint.y + data.position.y);
        } else if (data.direction.equals(Direction.RIGHT)) {
            return new Vector2(
                    data.position.x + bodyHitPoint.x,
                    data.position.y + bodyHitPoint.y);
        }
        return null;
    }

    private void sword_slash_up_down() {
        //Frame 0
        Vector2[] body = {
                v(23, 38), v(39, 38),
                v(27, 34), v(39, 34),
                v(28, 21), v(39, 21),
                v(23, 31), v(36, 31),
                v(24, 27), v(35, 27),
                v(30, 19), v(40, 19),
                v(31, 17), v(40, 17)
        };
        bodyFrames.put(0, body);
        Vector2[] attack = {};
        attackFrames.put(0, attack);

        //Frame 1
        body = new Vector2[]{
                v(24, 38), v(39, 38),
                v(26, 35), v(40, 35),
                v(26, 32), v(37, 32),
                v(26, 28), v(35, 28),
                v(25, 25), v(35, 25),
                v(27, 22), v(38, 22),
                v(31, 18), v(40, 18),
                v(32, 15), v(39, 15)
        };
        bodyFrames.put(1, body);
        attack = new Vector2[]{};
        attackFrames.put(1, attack);

        //Frame 3
        attack = new Vector2[]{
                v(18, 37), v(47, 37),
                v(44, 28), v(54, 28),
                v(45, 24), v(54, 24),
                v(44, 17), v(52, 17),
                v(36, 11), v(44, 11),
                v(21, 33), v(50, 33)
        };
        attackFrames.put(3, attack);
        body = new Vector2[]{
                v(23, 30), v(40, 30),
                v(24, 28), v(40, 28),
                v(26, 26), v(38, 26),
                v(28, 25), v(39, 25),
                v(32, 22), v(39, 22),
                v(33, 20), v(36, 20)
        };
        bodyFrames.put(3, body);

        //Frame 4
        attack = new Vector2[]{
                v(9, 33), v(24, 33),
                v(12, 31), v(19, 31),
                v(12, 34), v(26, 34)
        };
        attackFrames.put(4, attack);
        body = new Vector2[]{
                v(33, 19), v(36, 19),
                v(32, 21), v(39, 21),
                v(26, 32), v(40, 32),
                v(18, 29), v(40, 29),
                v(25, 25), v(38, 25)
        };
        bodyFrames.put(4, body);

        bodyHitBoxes.put(AnimationName.SWORD_SLASH_UP_DOWN_STANDING, bodyFrames);
        attackHitBoxes.put(AnimationName.SWORD_SLASH_UP_DOWN_STANDING, attackFrames);
    }

    private void sword_slash_spin() {
        bodyFrames = new HashMap<Integer, Vector2[]>();
        attackHitBoxes.put(AnimationName.SWORD_SLASH_SPIN, bodyFrames);
    }

    private Vector2 v(int x, int y) {
        return new Vector2(x, Paths.PLAYER_KEYFRAME_HEIGHT - y);
    }
}
