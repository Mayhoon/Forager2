package player;

import Enums.Entity;
import animations.Animator;
import animations.HitBox;
import animations.PlayerInputAnimationMapper;
import camera.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import networking.NetworkData;
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

    public Player(Entity entity, ServerClientWrapper wrapper, Camera camera) {
        this.entity = entity;
        this.wrapper = wrapper;
        this.camera = camera;
        animator = new Animator(wrapper, entity);
        playerMotor = new PlayerMotor(wrapper);
        playerInputAnimationMapper = new PlayerInputAnimationMapper(wrapper, entity, animator);
        gamePadInput = new GamePadInput(entity, playerMotor, playerInputAnimationMapper);
        hitBox = new HitBox();
    }

    public void render(SpriteBatch batch) {
        playerMotor.render();
        animator.update(batch);
        camera.move(wrapper.ownData().position);
        hitBox.drawHitboxes();
        wrapper.sendTCP();
    }
}
