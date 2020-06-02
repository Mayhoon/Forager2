package animations;

import Enums.AnimationName;
import Enums.Direction;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import networking.State;
import player.PlayerAnimations;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class Animator {
    private State state;
    private float elapsedTime = 0f;
    private TextureRegion region;
    private TextureRegion keyFrame;
    private Direction currentDirection;
    private int xDirection;
    private PlayerAnimations playerAnimations;

    public Animator(State playerData) {
        this.state = playerData;
        this.playerAnimations = new PlayerAnimations();
        this.xDirection = -1;
        textureRegion();
    }

    private void textureRegion() {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.drawPixel(0, 0);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        region = new TextureRegion(texture, 0, 0, 1, 1);
    }

    public void update(SpriteBatch batch, float delta) {
        elapsedTime += delta;
        state.elapsedTime = elapsedTime;
        ShapeDrawer shapedrawer = new ShapeDrawer(batch, region);

        state.keyFrameIndex = playerAnimations.get(state.animation).getKeyFrameIndex(elapsedTime);
        keyFrame = playerAnimations.get(state.animation).getKeyFrames()[state.keyFrameIndex];

        batch.draw(
                keyFrame,
                state.position.x,
                state.position.y,
                keyFrame.getRegionWidth() / 2,
                keyFrame.getRegionHeight() / 2,
                keyFrame.getRegionWidth(), keyFrame.getRegionHeight(),
                direction(state),
                1,
                0);
    }

    private int direction(State data) {
        if (data.direction.equals(Direction.LEFT)) {
            return -1;
        } else if (data.direction.equals(Direction.RIGHT)) {
            return 1;
        }
        return 1;
    }

    public void setAnimation(AnimationName animationName) {
        state.animation = animationName;
    }

    public int getKeyFrameIndex() {
        return playerAnimations.get(state.animation).getKeyFrameIndex(elapsedTime);
    }

    public int getAnimationWidth() {
        return playerAnimations.get(state.animation).getKeyFrame(elapsedTime).getRegionWidth();
    }

    public float getAnimationWidth(AnimationName animationName) {
        return playerAnimations.get(animationName).getKeyFrame(elapsedTime).getRegionWidth();
    }

    public int getAnimationHeight() {
        return playerAnimations.get(state.animation).getKeyFrame(elapsedTime).getRegionHeight();
    }

    public float getAnimationHeight(AnimationName animationName) {
        return playerAnimations.get(animationName).getKeyFrame(elapsedTime).getRegionHeight();
    }
}
