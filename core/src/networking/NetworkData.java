package networking;

import Enums.AnimationName;
import Enums.Direction;
import com.badlogic.gdx.math.Vector2;

public class NetworkData {
    public Vector2 position;
    public Direction direction;
    public Boolean moving;
    public float movementSpeed;

    //Animations
    public AnimationName animation;

    public NetworkData() {
        position = new Vector2(0, 0);
        direction = Direction.NONE;
        animation = AnimationName.IDLE_SWORD_NOT_DRAWN;
        moving = false;
        movementSpeed = 0f;
    }
}
