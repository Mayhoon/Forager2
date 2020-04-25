package player;

import Enums.Entity;
import animations.Animator;
import animations.HitBox;
import animations.PlayerInputAnimationMapper;
import camera.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import networking.ServerClientWrapper;

public class Player {
    public Entity entity;
    private ServerClientWrapper wrapper;
    private Camera camera;
    private GamePadInput gamePadInput;
    private PlayerMotor playerMotor;
    private HitBox hitBox;
    private Animator animator;
    private PlayerInputAnimationMapper playerInputAnimationMapper;
    private SpriteBatch batch;

    public Player(Entity entity, ServerClientWrapper wrapper, Camera camera, SpriteBatch batch) {
        this.entity = entity;
        this.wrapper = wrapper;
        this.camera = camera;
        this.batch = batch;
        animator = new Animator(wrapper, entity);
        playerMotor = new PlayerMotor(wrapper);
        playerInputAnimationMapper = new PlayerInputAnimationMapper(wrapper, entity, animator);
        gamePadInput = new GamePadInput(entity, playerMotor, playerInputAnimationMapper);
        hitBox = new HitBox(batch);
    }

    public void render() {
        playerMotor.render();
        camera.move(wrapper.ownData().position);
        animator.update(batch);
        //  wrapper.ownData().animation = AnimationName.SWORD_SLASH_UP_DOWN_STANDING;
        hitBox.drawHitboxes(wrapper.ownData().animation, animator.getKeyFrameIndex());
        wrapper.sendTCP();
    }
}
