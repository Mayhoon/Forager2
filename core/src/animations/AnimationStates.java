package animations;

import Enums.AnimationState;
import Enums.Button;
import Enums.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import config.Paths;
import networking.ServerClientWrapper;

public class AnimationStates {
    private Animations animations;

    public AnimationStates(ServerClientWrapper wrapper, Entity entity) {
        animations = new Animations(Paths.PLAYER_ANIMATION, 8, 10, wrapper, entity);
        animations.add(AnimationState.IDLE_SWORD_NOT_DRAWN, 0.05f, 0, 0, 4);
        animations.add(AnimationState.IDLE_SWORD_DRAWN, 0.05f, 0, 5, 8);
        animations.add(AnimationState.DRAW_SWORD, 0.05f, 3, 0, 4);
        animations.set(AnimationState.IDLE_SWORD_NOT_DRAWN);
    }

    public void update(SpriteBatch batch, Vector3 position) {
        Vector2 p = new Vector2(position.x, position.y);
        animations.update(p, batch);
    }

    public void buttonPressed(Button button) {
        if (button.equals(Button.X)) {
            animations.set(AnimationState.DRAW_SWORD);
        }
    }
}
