package animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationLoader {
    private TextureRegion[][] allFrames;

    public AnimationLoader(String path, int tilesPerRow, int tilesPerColumn) {
        Texture bundledTextures = new Texture(path);
        int individualTextureWidth = bundledTextures.getWidth() / tilesPerRow;
        int individualTextureHeight = bundledTextures.getHeight() / tilesPerColumn;
        allFrames = TextureRegion.split(bundledTextures, individualTextureWidth, individualTextureHeight);
    }

    //Get animation frames
    public TextureRegion[] load (int col, int startFrame, int endFrame) {
        int numberOfFrames = endFrame - startFrame;
        TextureRegion[] animationFrames = new TextureRegion[numberOfFrames];

        int index = 0;
        for (int row = startFrame; row < endFrame; row++) {
            animationFrames[index] = allFrames[col][row];
            index++;
        }
       return animationFrames;
    }
}
