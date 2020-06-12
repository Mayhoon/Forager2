package collision;

import Enums.AnimationName;
import com.badlogic.gdx.math.Vector2;

import java.util.Map;

public class Sword_Slash_Up_Down extends AnimationHitBox {

    public Sword_Slash_Up_Down(
            Map<Integer, Vector2[]> bodyFrames,
            Map<Integer, Vector2[]> attackFrames,
            Map<AnimationName, Map<Integer, Vector2[]>> attackHitBoxes,
            Map<AnimationName, Map<Integer, Vector2[]>> bodyHitBoxes) {

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
        Vector2[] attack = {
                v(0, 0), v(0, 0),
        };
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
        attack = new Vector2[]{
                v(0, 0), v(0, 0),
        };
        attackFrames.put(1, attack);

        //Frame 2
        body = new Vector2[]{
                v(24, 38), v(40, 38),
                v(26, 35), v(40, 35),
                v(26, 32), v(37, 32),
                v(26, 28), v(35, 28),
                v(25, 25), v(35, 25),
                v(27, 22), v(38, 22),
                v(31, 18), v(40, 18),
                v(32, 15), v(39, 15)
        };
        bodyFrames.put(2, body);
        attack = new Vector2[]{
                v(0, 0), v(0, 0),
        };
        attackFrames.put(2, attack);


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

        //Frame 5
        attack = new Vector2[]{
                v(0, 0), v(0, 0),
        };
        attackFrames.put(5, attack);
        body = new Vector2[]{
                v(22, 38), v(38, 38),
                v(23, 32), v(39, 32),
                v(24, 29), v(39, 29),
                v(27, 26), v(37, 26),
                v(27, 23), v(39, 23),
                v(32, 20), v(39, 20),
                v(33, 18), v(36, 18)
        };
        bodyFrames.put(5, body);

        bodyHitBoxes.put(AnimationName.SWORD_SLASH_UP_DOWN, bodyFrames);
        attackHitBoxes.put(AnimationName.SWORD_SLASH_UP_DOWN, attackFrames);
    }
}
