package player;

import Enums.Entity;
import animations.Animator;
import animations.CollisionChecker;
import animations.PlayerInputAnimationMapper;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import networking.Network;

public class Player {
    private Entity entity;
    private GamePadInput gamePadInput;
    private PlayerMotor playerMotor;
    private CollisionChecker collisionChecker;
    private Animator animator;
    private PlayerInputAnimationMapper playerInputAnimationMapper;
    private SpriteBatch batch;
    private Network network;

    public Player(SpriteBatch batch, Network network, Entity entity) {
        this.batch = batch;
        this.network = network;
        this.entity = entity;

        if (entity.equals(Entity.Player)) {
            playerMotor = new PlayerMotor(network.player());
            animator = new Animator(network.player());
            playerInputAnimationMapper = new PlayerInputAnimationMapper(animator);
            gamePadInput = new GamePadInput(playerMotor, playerInputAnimationMapper);
        } else {
            playerMotor = new PlayerMotor(network.opponent());
            animator = new Animator(network.opponent());
        }
        collisionChecker = new CollisionChecker(batch, animator, network.player(), network.opponent());
    }

    public void update(float delta) {
        playerMotor.calculatePosition(delta);
    }

    public void render(float delta) {
        animator.update(batch, delta);
        //collisionChecker.attackCollisionPoints();
    }
}
