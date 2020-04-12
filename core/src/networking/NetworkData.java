package networking;

import Enums.AnimationState;
import com.badlogic.gdx.math.Vector2;

public class NetworkData {
    public float ownPositionX = 0f;
    public float ownPositionY = 0f;
    public float otherPositionX = 0f;
    public float otherPositionY = 0f;
   // Vector2 position = new Vector2(0,0);

    //Animations
    public AnimationState ownAnimationState = AnimationState.IDLE_SWORD_NOT_DRAWN;
    public AnimationState otherAnimationState = AnimationState.IDLE_SWORD_NOT_DRAWN;
    public float ownElapsedTime = 0f;
    public float otherElapsedTime = 0f;


   // private static Json playerJSON;

    public NetworkData() {
//        playerJSON = new Json();
//        File file = new File(Resources.JSON_PLAYER_POSITION);
    }
}
