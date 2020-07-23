package player;

import enums.AnimationName;
import animations.Animator;
import collision.BodyColliders;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import networking.CharacterData;

public class Player {
    private GamePadInput gamePadInput;
    private PlayerMotor playerMotor;
    private Animator animator;
    private SpriteBatch batch;
    private BodyColliders bodyColliders;

    public Player(SpriteBatch batch, CharacterData state, boolean enableControls, com.gushikustudios.rube.RubeScene scene) {
        this.batch = batch;
        bodyColliders = new BodyColliders(scene);
        animator = new Animator();

        if (enableControls) {
            playerMotor = new PlayerMotor(state);
            gamePadInput = new GamePadInput(playerMotor, state);
        }
    }

    public void update(float delta) {
        playerMotor.calculatePosition(delta);
    }

    public void render(float delta, CharacterData data) {
        bodyColliders.updatePositions(data.position, AnimationName.IDLE_SWORD_NOT_DRAWN);
        animator.update(batch, delta, data);
    }
}
