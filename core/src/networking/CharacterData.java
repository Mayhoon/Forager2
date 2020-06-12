package networking;

import Enums.AnimationName;
import Enums.Direction;
import com.badlogic.gdx.math.Vector2;

public class CharacterData {
    public Vector2 position;
    public Direction direction;
    public Boolean moving;
    public float movementSpeed;
    public float elapsedTime;
    public float health;
    public int keyFrameIndex;
    public AnimationName animation;

    public CharacterData() {
        position = new Vector2(0, 0);
        direction = Direction.RIGHT;
        animation = AnimationName.SWORD_SLASH_UP_DOWN;
        keyFrameIndex = 1;

        moving = false;
        movementSpeed = 0f;
        health = 100f;
    }
}
