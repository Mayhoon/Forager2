package animations;

import Enums.AnimationState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AnimationHandler {
    private TextureRegion[][] allFrames;
    private AnimationState animationState;
    Map<AnimationState, Animation<TextureRegion>> map;
    private float elapsedTime = 0;

    public AnimationHandler(String path, int tilesPerRow, int tilesPerColumn) {
        map = new HashMap<>();
        Texture bundledTextures = new Texture(path);
        int individualTextureWidth = bundledTextures.getWidth() / tilesPerRow;
        int individualTextureHeight = bundledTextures.getHeight() / tilesPerColumn;
        allFrames = TextureRegion.split(bundledTextures, individualTextureWidth, individualTextureHeight);
    }

    public void setAnimation(AnimationState state) {
        animationState = state;
    }

    public void update(Vector3 entityPosition, SpriteBatch batch) {
        elapsedTime += (Gdx.graphics.getDeltaTime());
        System.out.println(map.size());
        TextureRegion keyFrame = map.get(animationState).getKeyFrame(elapsedTime, true);
        batch.draw(keyFrame, entityPosition.x, entityPosition.y);
    }

    //Get animation frames
    public void addAnimation(AnimationState animationState, float timeBetweenFrames, int col, int startFrame, int endFrame) {
        int numberOfFrames = endFrame - startFrame;
        TextureRegion[] animationFrames = new TextureRegion[numberOfFrames];

        int index = 0;
        for (int row = startFrame; row < numberOfFrames; row++) {
            animationFrames[index] = allFrames[col][row];
            index++;
        }

        Animation<TextureRegion> animation = new Animation<>(timeBetweenFrames, animationFrames);
        map.put(animationState, animation);
    }

}
