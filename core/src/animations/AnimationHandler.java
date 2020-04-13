package animations;


import Enums.AnimationState;
import Enums.Buttons;
import Enums.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import config.Paths;
import networking.ServerClientWrapper;

public class AnimationHandler {
    private Animations animations;

    public AnimationHandler(ServerClientWrapper wrapper, Entity entity) {
        animations = new Animations(Paths.PLAYER_ANIMATION, 8, 10, wrapper, entity);
        animations.add(AnimationState.IDLE_SWORD_NOT_DRAWN, 0.05f, 0, 0, 4);
        animations.add(AnimationState.IDLE_SWORD_DRAWN, 0.05f, 0, 5, 8);
        animations.add(AnimationState.DRAW_SWORD, 0.05f, 3, 0, 4);
        animations.set(AnimationState.IDLE_SWORD_NOT_DRAWN, false);
    }

    public void update(SpriteBatch batch, Vector2 position) {
        animations.update(position, batch);
    }

    public void buttonPressed(Buttons button) {
        if (button.equals(Buttons.X)) {
          animations.set(AnimationState.DRAW_SWORD, false);
        }
    }
}
