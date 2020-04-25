package animations;

import Enums.AnimationName;
import com.badlogic.gdx.math.Vector2;

import java.util.Map;

public class HitBox {
    private Map animations;
    private int size = 2;
    private Vector2[][] range = new Vector2[size][2];

    public HitBox() {
        range[0][0] = new Vector2(5, 9);
        range[0][1] = new Vector2(12, 9);

        range[1][0] = new Vector2(14, 0);
        range[1][1] = new Vector2(21, 0);
    }

    public void getHitPoints(AnimationName animationName, int frameIndex) {

    }

    public void drawHitboxes() {
        for (int i = 0; i < 1; i++) {
            System.out.println("-----------");
            System.out.println(range[i][0]);
            System.out.println(range[i][1]);
        }
    }

}
