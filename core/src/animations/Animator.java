package animations;

import Enums.AnimationState;
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
import sun.java2d.pipe.SpanShapeRenderer;

import java.util.List;

public class Animator {
    private ServerClientWrapper wrapper;
    private Entity entity;
    private Animations animations;
    private AnimationState animationState;
    private List<String> animationQueue;
    private float elapsedTime = 0f;
    private  TextureRegion region;

    private ShapeRenderer shapeRenderer;

    public Animator(ServerClientWrapper wrapper, Entity entity) {
        this.wrapper = wrapper;
        this.entity = entity;

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
        animations.add(AnimationState.SWORD_SLASH_SPIN, 0.1f,2, 0, 6);
        setAnimation(AnimationState.IDLE_SWORD_NOT_DRAWN);
    }

    public void update(SpriteBatch batch, Vector2 position) {
        elapsedTime += (Gdx.graphics.getDeltaTime());
        ShapeDrawer shapedrawer = new ShapeDrawer(batch, region);

        if (entity.equals(Entity.Player)) {
            TextureRegion keyFrame = animations.getAnimation(animationState).getKeyFrame(0, false);
            batch.draw(keyFrame, position.x, position.y);
            wrapper.ownData().animation = animationState;

            shapedrawer.rectangle(position.x, position.y, 2, 2);

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
