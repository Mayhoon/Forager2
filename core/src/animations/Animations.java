package animations;

import Enums.AnimationState;
import Enums.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import networking.ServerClientWrapper;
import player.Player;

import java.util.HashMap;
import java.util.Map;

public class Animations {
    private TextureRegion[][] allFrames;
    private AnimationState animationState;
    Map<AnimationState, Animation<TextureRegion>> map;
    private float elapsedTime = 0;
    private ServerClientWrapper wrapper;
    private Entity entity;
    private boolean test;

    public Animations(String path, int tilesPerRow, int tilesPerColumn, ServerClientWrapper wrapper, Entity entity) {
        Texture bundledTextures = new Texture(path);
        int individualTextureWidth = bundledTextures.getWidth() / tilesPerRow;
        int individualTextureHeight = bundledTextures.getHeight() / tilesPerColumn;
        this.wrapper = wrapper;
        this.entity = entity;

        if (entity.equals(Entity.Player)) {
            test = true;
        } else {
            test = false;
        }

        map = new HashMap<>();
        allFrames = TextureRegion.split(bundledTextures, individualTextureWidth, individualTextureHeight);
    }

    public void set(AnimationState state) {
        animationState = state;
    }

    public void update(Vector2 entityPosition, SpriteBatch batch) {
        elapsedTime += (Gdx.graphics.getDeltaTime());
        TextureRegion keyFrame = map.get(animationState).getKeyFrame(elapsedTime, true);
        wrapper.data().ownAnimationState = animationState;
        wrapper.data().ownKeyFrame = keyFrame;

        if (test && keyFrame != null) {
            System.out.println("ENTITY: " + entity);
            batch.draw(keyFrame, entityPosition.x, entityPosition.y);
        } else {
            batch.draw(wrapper.data().otherKeyFrame, wrapper.data().otherPositionX, wrapper.data().otherPositionY);
        }
    }

    //Get animation frames
    public void add(AnimationState animationState, float timeBetweenFrames, int col, int startFrame, int endFrame) {
        int numberOfFrames = endFrame - startFrame;
        TextureRegion[] animationFrames = new TextureRegion[numberOfFrames];

        int index = 0;
        for (int row = startFrame; row < endFrame; row++) {
            animationFrames[index] = allFrames[col][row];
            index++;
        }
        Animation<TextureRegion> animation = new Animation<>(timeBetweenFrames, animationFrames);
        map.put(animationState, animation);
    }

}
