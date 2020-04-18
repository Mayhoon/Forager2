package animations;

import Enums.AnimationState;
import Enums.Direction;
import Enums.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import config.Paths;
import networking.ServerClientWrapper;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.util.List;

public class Animator {
    private ServerClientWrapper wrapper;
    private Entity entity;
    private Animations animations;
    private AnimationState animationState;
    private List<String> animationQueue;
    private float elapsedTime = 0f;
    private TextureRegion region;
    private TextureRegion keyFrame;
    private Direction currentDirection;
    private int xDirection;

    private ShapeRenderer shapeRenderer;

    public Animator(ServerClientWrapper wrapper, Entity entity) {
        this.wrapper = wrapper;
        this.entity = entity;
        this.xDirection = -1;

        //Debug renderer for shapes
        shapeRenderer = new ShapeRenderer();
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawPixel(0, 0);
        Texture texture = new Texture(pixmap); //remember to dispose of later
        pixmap.dispose();
        region = new TextureRegion(texture, 0, 0, 1, 1);

        animations = new Animations(Paths.PLAYER_ANIMATION, 8, 10);
        animations.add(AnimationState.IDLE_SWORD_NOT_DRAWN, 0.1f, 0, 0, 4);
        animations.add(AnimationState.IDLE_SWORD_DRAWN, 0.1f, 0, 5, 8);
        animations.add(AnimationState.DRAW_SWORD, 0.1f, 3, 0, 4);
        animations.add(AnimationState.SWORD_SLASH_UP_DOWN_STANDING, 0.1f, 1, 0, 6);
        animations.add(AnimationState.SWORD_SLASH_SPIN, 0.1f, 2, 0, 6);
        setAnimation(AnimationState.IDLE_SWORD_NOT_DRAWN);
    }

    public void update(SpriteBatch batch, Vector2 position, Direction direction) {
        elapsedTime += (Gdx.graphics.getDeltaTime());
        ShapeDrawer shapedrawer = new ShapeDrawer(batch, region);

        if (entity.equals(Entity.Player)) {
            keyFrame = animations.getAnimation(animationState).getKeyFrame(elapsedTime, true);

            if (direction.equals(Direction.LEFT)) {
                xDirection = -1;
            } else if (direction.equals(Direction.RIGHT)) {
                xDirection = 1;
            }

            batch.draw(keyFrame, position.x, position.y, keyFrame.getRegionWidth() / 2, keyFrame.getRegionHeight() / 2, keyFrame.getRegionWidth(), keyFrame.getRegionHeight(), xDirection, 1, 0);
            wrapper.ownData().animation = animationState;
            shapedrawer.rectangle(position.x, position.y, keyFrame.getRegionWidth(), keyFrame.getRegionHeight());

        } else if (entity.equals(Entity.Opponent)) {
            TextureRegion keyFrame = animations.getAnimation(wrapper.opponentData().animation).getKeyFrame(elapsedTime, true);
            batch.draw(keyFrame, wrapper.opponentData().position.x, wrapper.opponentData().position.y);
            shapedrawer.rectangle(wrapper.opponentData().position.x, wrapper.opponentData().position.y, keyFrame.getRegionWidth(), keyFrame.getRegionHeight());
        }
    }

    public void setAnimation(AnimationState animationState) {
        this.animationState = animationState;
        wrapper.ownData().animation = animationState;
    }
}
