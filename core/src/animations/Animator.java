package animations;

import Enums.AnimationState;
import Enums.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import config.Paths;
import networking.ServerClientWrapper;

import java.util.List;

public class Animator {
    private ServerClientWrapper wrapper;
    private Entity entity;
    private Animations animations;
    private AnimationState animationState;
    private List<String> animationQueue;
    private float elapsedTime = 0f;

    public Animator(ServerClientWrapper wrapper, Entity entity) {
        this.wrapper = wrapper;
        this.entity = entity;

        animations = new Animations(Paths.PLAYER_ANIMATION, 8, 10);
        animations.add(AnimationState.IDLE_SWORD_NOT_DRAWN, 0.1f, 0, 0, 4);
        animations.add(AnimationState.IDLE_SWORD_DRAWN, 0.1f, 0, 5, 8);
        animations.add(AnimationState.DRAW_SWORD, 0.1f, 3, 0, 4);
        animations.add(AnimationState.SWORD_SLASH_UP_DOWN_STANDING, 0.1f, 1, 0, 6);
        animations.add(AnimationState.SWORD_SLASH_SPIN, 0.1f,2, 0, 6);
        setAnimation(AnimationState.IDLE_SWORD_NOT_DRAWN);
    }

    public void update(SpriteBatch batch, Vector2 position) {
        elapsedTime += (Gdx.graphics.getDeltaTime());

        if (entity.equals(Entity.Player)) {
            TextureRegion keyFrame = animations.getAnimation(animationState).getKeyFrame(elapsedTime, true);
            batch.draw(keyFrame, position.x, position.y);
            wrapper.ownData().animation = animationState;

        } else if (entity.equals(Entity.Opponent)) {
            TextureRegion keyFrame = animations.getAnimation(wrapper.opponentData().animation).getKeyFrame(elapsedTime, true);
            batch.draw(keyFrame, wrapper.opponentData().position.x, wrapper.opponentData().position.y);
        }
    }

    public void setAnimation(AnimationState animationState) {
        this.animationState = animationState;
        wrapper.ownData().animation = animationState;
    }
}
