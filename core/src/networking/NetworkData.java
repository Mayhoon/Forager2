package networking;

import Enums.AnimationName;
import Enums.Direction;
import com.badlogic.gdx.math.Vector2;

public class NetworkData {
    public Vector2 position;
    public Direction direction;
    public Boolean moving;
    public float movementSpeed;
    public float elapsedTime;
    public float health;
    public int keyFrameIndex;

    //Animations
    public AnimationName animation;

    public NetworkData() {
        position = new Vector2(0, 0);
        direction = Direction.RIGHT;
        animation = AnimationName.SWORD_SLASH_UP_DOWN_STANDING;
        keyFrameIndex = 4;

        moving = false;
        movementSpeed = 0f;
        elapsedTime = 0f;
        health = 100f;
    }
}
