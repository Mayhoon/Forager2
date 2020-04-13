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

import java.util.HashMap;
import java.util.Map;

public class Animations {
    private TextureRegion[][] allFrames;
    private AnimationState animation;
    private Map<AnimationState, com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>> map;
    private float elapsedTime = 0;
    private ServerClientWrapper wrapper;
    private Entity entity;

    public Animations(String path, int tilesPerRow, int tilesPerColumn, ServerClientWrapper wrapper, Entity entity) {
        Texture bundledTextures = new Texture(path);
        int individualTextureWidth = bundledTextures.getWidth() / tilesPerRow;
        int individualTextureHeight = bundledTextures.getHeight() / tilesPerColumn;
        this.wrapper = wrapper;
        this.entity = entity;

        map = new HashMap<>();
        allFrames = TextureRegion.split(bundledTextures, individualTextureWidth, individualTextureHeight);
    }

    public void set(AnimationState state, boolean loop) {
        animation = state;
        wrapper.ownData().animation = state;
    }

    public void update(Vector2 position, SpriteBatch batch) {
        elapsedTime += (Gdx.graphics.getDeltaTime());

        if (entity.equals(Entity.Player)) {
            TextureRegion keyFrame = map.get(animation).getKeyFrame(elapsedTime, true);
            batch.draw(keyFrame, position.x, position.y);
            wrapper.ownData().animation = animation;

        } else if (entity.equals(Entity.Opponent)) {
            TextureRegion keyFrame = map.get(wrapper.opponentData().animation).getKeyFrame(elapsedTime, true);
            batch.draw(keyFrame, wrapper.opponentData().position.x, wrapper.opponentData().position.y);
        }
    }

    //Get animation frames
    public void add(AnimationState animations, float timeBetweenFrames, int col, int startFrame, int endFrame) {
        int numberOfFrames = endFrame - startFrame;
        TextureRegion[] animationFrames = new TextureRegion[numberOfFrames];

        int index = 0;
        for (int row = startFrame; row < endFrame; row++) {
            animationFrames[index] = allFrames[col][row];
            index++;
        }
        Animation<TextureRegion> animation = new Animation<>(timeBetweenFrames, animationFrames);
        map.put(animations, animation);
    }

}
