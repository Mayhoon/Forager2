package animations;


import Enums.AnimationState;
import Enums.Buttons;
import Enums.Direction;
import Enums.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import jdk.nashorn.internal.ir.BaseNode;
import networking.ServerClientWrapper;

public class AnimationHandler {
    private Animator animator;

    public AnimationHandler(ServerClientWrapper wrapper, Entity entity) {
        animator = new Animator(wrapper, entity);
    }

    public void update(SpriteBatch batch, Vector2 position) {
        animator.update(batch, position, Direction.LEFT);
    }

    public void buttonPressed(Buttons button) {
        switch (button) {
            case X:
                animator.setAnimation(AnimationState.DRAW_SWORD);
                break;
            case RB:
                animator.setAnimation(AnimationState.SWORD_SLASH_SPIN);
                break;
            case A:
                animator.setAnimation(AnimationState.SWORD_SLASH_UP_DOWN_STANDING);
                break;
            case Y:
                animator.setAnimation(AnimationState.IDLE_SWORD_NOT_DRAWN);
                break;
            case LB:
                animator.setAnimation(AnimationState.IDLE_SWORD_DRAWN);
                break;
            default:
                animator.setAnimation(AnimationState.IDLE_SWORD_NOT_DRAWN);
        }
    }
}
