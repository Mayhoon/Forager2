package animations;

import Enums.AnimationState;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

public class Animations {
    private TextureRegion[][] allFrames;
    private Map<AnimationState, Animation<TextureRegion>> map;

    public Animations(String path, int tilesPerRow, int tilesPerColumn) {
        Texture bundledTextures = new Texture(path);
        int individualTextureWidth = bundledTextures.getWidth() / tilesPerRow;
        int individualTextureHeight = bundledTextures.getHeight() / tilesPerColumn;
        map = new HashMap<>();
        allFrames = TextureRegion.split(bundledTextures, individualTextureWidth, individualTextureHeight);
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

    public Animation<TextureRegion> getAnimation(AnimationState animation) {
        return map.get(animation);
    }
}
