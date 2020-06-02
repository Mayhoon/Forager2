package animations;

import Enums.AnimationName;
import Enums.Buttons;

public class PlayerInputAnimationMapper {
    private Animator animator;

    public PlayerInputAnimationMapper(Animator animator) {
        this.animator = animator;
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
                animator.setAnimation(AnimationName.SWORD_SLASH_UP_DOWN);
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
