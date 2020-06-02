package player;

import Enums.Entity;
import animations.Animator;
import animations.CollisionChecker;
import animations.PlayerInputAnimationMapper;
import camera.Camera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import networking.Network;

public class Player {
    private Entity entity;
    private Camera camera;
    private GamePadInput gamePadInput;
    private PlayerMotor playerMotor;
    private CollisionChecker collisionChecker;
    private Animator animator;
    private PlayerInputAnimationMapper playerInputAnimationMapper;
    private SpriteBatch batch;
    private Network network;

    public Player(SpriteBatch batch, Camera camera, Network network, Entity entity) {
        this.camera = camera;
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

    public void render(float delta) {
        if (playerMotor != null) {
            playerMotor.calculatePosition(delta);
        }
        //camera.move(wrapper.ownData().position);
        animator.update(batch, delta);

        try {
            collisionChecker.attackCollisionPoints();
        } catch (Exception e) {
            //System.out.println("Animation " + wrapper.ownData().animation + " missing colliders");
        }
    }
}
