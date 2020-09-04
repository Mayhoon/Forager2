package player;

import enums.AnimationName;
import animations.Animator;
import collision.Collider;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import networking.CharacterData;
import com.gushikustudios.rube.RubeScene;

public class Player {
    private GamePadInput gamePadInput;
    private PlayerMotor playerMotor;
    private Animator animator;
    private SpriteBatch batch;
    private Collider collider;


    public Player(SpriteBatch batch, CharacterData state, boolean enableControls, RubeScene scene) {
        this.batch = batch;
        collider = new Collider(scene);
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
        collider.deactivate();
        collider.updatePositions(data.position, AnimationName.IDLE_SWORD_NOT_DRAWN, data.direction, data.keyFrameIndex);
        animator.update(batch, delta, data);
    }
}
