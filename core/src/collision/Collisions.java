package collision;

import Enums.AnimationName;
import Enums.Direction;
import com.badlogic.gdx.math.Vector2;
import config.Paths;
import networking.ServerClientWrapper;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.DeflaterInputStream;

public class Collisions {
    private ServerClientWrapper wrapper;
    private Map<AnimationName, Map<Integer, Vector2[]>> attackHitBoxes;
    private Map<Integer, Vector2[]> attackFrames;

    private Map<AnimationName, Map<Integer, Vector2[]>> bodyHitBoxes;
    private Map<Integer, Vector2[]> bodyFrames;

    public Collisions(ServerClientWrapper wrapper) {
        this.wrapper = wrapper;
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

    //Return the world coordinate
    public Vector2 hitPoint(Vector2 player, int index, AnimationName animationName, Direction direction, int keyFrameIndex) {
        Vector2 hitPoint = getAttackHitboxes(animationName, keyFrameIndex)[index];

        if (direction.equals(Direction.LEFT)) {
            return new Vector2((player.x + 64) - hitPoint.x, hitPoint.y + player.y);
        } else if (direction.equals(Direction.RIGHT)) {
            return new Vector2(player.x + hitPoint.x, player.y + hitPoint.y);
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
                v(36, 11), v(44, 11)
        };
        attackFrames.put(3, attack);
        body = new Vector2[]{
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
