package animations;

import Enums.PlayerState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class AnimationHandler {
    private Texture bundledTextures;
    private TextureRegion[] animationFrames;
    private Animation<TextureRegion> walkAnimation;
    private ArrayList<Animation<TextureRegion>> animations;

    private float elapsedTime = 0;

    public AnimationHandler(float timeBetweenFrames, String path, int tilesPerRow, int tilesPerColumn) {
        bundledTextures = new Texture(path);
        int amountOfImages = tilesPerColumn * tilesPerRow;
        int individualTextureWidth = bundledTextures.getWidth() / tilesPerRow;
        int individualTextureHeight = bundledTextures.getHeight() / tilesPerColumn;

        TextureRegion[][] tmpFrames = TextureRegion.split(bundledTextures, individualTextureWidth, individualTextureHeight);
        animationFrames = new TextureRegion[amountOfImages];

        int frameIndex = 0;
        for (int column = 0; column < amountOfImages; column++) {
            for (int row = 0; row < 1; row++) {
                animationFrames[frameIndex++] = tmpFrames[row][column];
            }
        }
        walkAnimation = new Animation<>(timeBetweenFrames, animationFrames);

        animations = new ArrayList<>();
        animations.add(walkAnimation);
    }

    public void update(Vector3 entityPosition, PlayerState STATE, SpriteBatch batch) {
        elapsedTime += (Gdx.graphics.getDeltaTime());

        switch (STATE) {
            case IDLE:
                batch.draw(animations.get(0).getKeyFrame(elapsedTime, true), entityPosition.x, entityPosition.y);
                break;
            case MOVING:
                batch.draw(animations.get(0).getKeyFrame(elapsedTime, true), entityPosition.x, entityPosition.y);
                break;
            default:
                System.out.println("State cant be animated");
        }

    }
}
