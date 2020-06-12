package animations;

import Enums.Direction;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import networking.CharacterData;
import player.PlayerAnimations;

public class Animator {
    private float elapsedTime = 0f;
    private TextureRegion region;
    private TextureRegion keyFrame;
    private PlayerAnimations playerAnimations;

    public Animator() {
        this.playerAnimations = new PlayerAnimations();
    }

    public void update(SpriteBatch batch, float delta, CharacterData character) {
        elapsedTime += delta;

        character.keyFrameIndex = playerAnimations.get(character.animation).getKeyFrameIndex(elapsedTime);
        keyFrame = playerAnimations.get(character.animation).getKeyFrames()[character.keyFrameIndex];

        batch.draw(
                keyFrame,
                character.position.x,
                character.position.y,
                keyFrame.getRegionWidth() / 2,
                keyFrame.getRegionHeight() / 2,
                keyFrame.getRegionWidth(), keyFrame.getRegionHeight(),
                direction(character),
                1,
                0);
    }

    private int direction(CharacterData data) {
        if (data.direction.equals(Direction.LEFT)) {
            return -1;
        } else if (data.direction.equals(Direction.RIGHT)) {
            return 1;
        }
        return 1;
    }
}
