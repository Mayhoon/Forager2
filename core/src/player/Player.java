package player;

import animations.Animator;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import networking.CharacterData;

public class Player {
    private GamePadInput gamePadInput;
    private PlayerMotor playerMotor;
    private Animator animator;
    private SpriteBatch batch;

    public Player(SpriteBatch batch, CharacterData state, boolean enableControls) {
        this.batch = batch;
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
        animator.update(batch, delta, data);
    }
}
