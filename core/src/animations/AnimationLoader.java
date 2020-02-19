package animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationLoader {
    private Texture bundledAnimations;
    private TextureRegion[] animationFrames;
    public Animation<TextureRegion> animation;
    private float elapsedTime = 0;
    private float timeBetweenFrames;

    public AnimationLoader(float timeBetweenFrames, String path, int tilesPerRow, int tilesPerColumn) {
        this.timeBetweenFrames = timeBetweenFrames;
        bundledAnimations = new Texture(path);
        int amountOfImages = tilesPerColumn * tilesPerRow;

        TextureRegion[][] tmpFrames = TextureRegion.split(bundledAnimations, bundledAnimations.getWidth() / tilesPerRow,
                bundledAnimations.getHeight() / tilesPerColumn);
        animationFrames = new TextureRegion[amountOfImages];

        int frameIndex = 0;
        for (int column = 0; column < amountOfImages; column++) {
            for (int row = 0; row < 1; row++) {
                animationFrames[frameIndex++] = tmpFrames[row][column];
            }
        }
        animation = new Animation<>(timeBetweenFrames, animationFrames);
    }

    public void update(SpriteBatch batch) {
        elapsedTime += (Gdx.graphics.getDeltaTime());
        batch.draw(animation.getKeyFrame(elapsedTime, true), 100, 100);
        System.out.println("This is working");
    }
}
