package collision;

import Enums.AnimationName;
import Enums.Direction;
import com.badlogic.gdx.math.Vector2;
import networking.State;

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
        new Sword_Slash_Up_Down(attackFrames, bodyFrames, attackHitBoxes, bodyHitBoxes);
    }

    public Vector2[] getAttackHitboxes(AnimationName animationName, int index) {
        return attackHitBoxes.get(animationName).get(index);
    }

    public Vector2[] getBodyHitboxes(AnimationName animationName, int index) {
        return bodyHitBoxes.get(animationName).get(index);
    }

    //Return the world coordinate for either the player or the opponent
    public Vector2 attackHitPoint(int index, State playerData) {
        Vector2 attackHitPoint = getAttackHitboxes(playerData.animation, playerData.keyFrameIndex)[index];

        if (playerData.direction.equals(Direction.LEFT)) {
            return new Vector2((
                    playerData.position.x + 64) - attackHitPoint.x,
                    attackHitPoint.y + playerData.position.y);
        } else if (playerData.direction.equals(Direction.RIGHT)) {
            return new Vector2(
                    playerData.position.x + attackHitPoint.x,
                    playerData.position.y + attackHitPoint.y);
        }
        return null;
    }

    public Vector2 bodyHitPoint(int index, State data) {
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
}
