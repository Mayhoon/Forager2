package networking;

import Enums.AnimationState;
import com.badlogic.gdx.math.Vector2;

public class NetworkData {
//    public float ownPositionX = 0f;
//    public float ownPositionY = 0f;
//    public float otherPositionX = 0f;
//    public float otherPositionY = 0f;
    public Vector2 position = new Vector2(100,0);

    //Animations
    public AnimationState animation = AnimationState.IDLE_SWORD_NOT_DRAWN;

    public NetworkData() {

    }
}
