package animations;

import Enums.AnimationName;
import Enums.Buttons;
import Enums.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import networking.ServerClientWrapper;

public class PlayerInputAnimationMapper {
    private Animator animator;

    public PlayerInputAnimationMapper(ServerClientWrapper wrapper, Entity entity) {
        animator = new Animator(wrapper, entity);
    }

    public void update(SpriteBatch batch) {
        animator.update(batch);
    }

    public void buttonPressed(Buttons button) {
        switch (button) {
            case X:
                animator.setAnimation(AnimationName.DRAW_SWORD);
                break;
            case RB:
                animator.setAnimation(AnimationName.SWORD_SLASH_SPIN);
                break;
            case A:
                animator.setAnimation(AnimationName.SWORD_SLASH_UP_DOWN_STANDING);
                break;
            case Y:
                animator.setAnimation(AnimationName.IDLE_SWORD_NOT_DRAWN);
                break;
            case LB:
                animator.setAnimation(AnimationName.IDLE_SWORD_DRAWN);
                break;
            default:
                animator.setAnimation(AnimationName.IDLE_SWORD_NOT_DRAWN);
        }
    }
}
