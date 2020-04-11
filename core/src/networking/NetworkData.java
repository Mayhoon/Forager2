package networking;

import Enums.AnimationState;
import animations.AnimationStates;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class NetworkData {
    public float ownPositionX = 0f;
    public float ownPositionY = 0f;
    public float otherPositionX = 0f;
    public float otherPositionY = 0f;

    public AnimationState ownAnimationState = AnimationState.IDLE_SWORD_NOT_DRAWN;
    public AnimationState otherAnimationState = AnimationState.IDLE_SWORD_NOT_DRAWN;
    public TextureRegion ownKeyFrame = new TextureRegion();
    public TextureRegion otherKeyFrame = new TextureRegion();

   // private static Json playerJSON;

    public NetworkData() {
//        playerJSON = new Json();
//        File file = new File(Resources.JSON_PLAYER_POSITION);
    }
}
