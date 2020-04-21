package player;

import Enums.Direction;
import Enums.Entity;
import animations.PlayerInputAnimationMapper;
import camera.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import networking.ServerClientWrapper;

public class Player {
    public Entity entity;
    private ServerClientWrapper wrapper;
    private Camera camera;
    public Vector2 position;
    private GamePadInput gamePadInput;
    public PlayerInputAnimationMapper playerInputAnimationMapper;
    private Boolean moving;
    float previousAmount;

    public Player(Entity entity, ServerClientWrapper wrapper, Camera camera) {
        this.entity = entity;
        this.wrapper = wrapper;
        this.camera = camera;
        this.moving = false;
        this.previousAmount = 0f;
        this.playerInputAnimationMapper = new PlayerInputAnimationMapper(wrapper, entity);
        this.gamePadInput = new GamePadInput(this);
        this.position = new Vector2(0, 0);

    }

    public void render(SpriteBatch batch) {
        if (moving == true && wrapper.ownData().direction.equals(Direction.LEFT)) {
            wrapper.ownData().position.x += previousAmount;
            playerInputAnimationMapper.update(batch);

        } else if (moving == true && wrapper.ownData().direction.equals(Direction.RIGHT)) {
            wrapper.ownData().position.x += previousAmount;
            playerInputAnimationMapper.update(batch);

        } else {
            playerInputAnimationMapper.update(batch);
        }
        sendPlayerInformation();
    }

    private void sendPlayerInformation() {
        if (entity.equals(Entity.Opponent)) {
            position = wrapper.ownData().position;
        } else {
            camera.position.x = wrapper.ownData().position.x;
            camera.position.y = wrapper.ownData().position.y;
        }
        wrapper.sendTCP();
    }

    public void moveX(float amount) {
        //Find out to which direction the player faces
        if (amount < -0.045f) {
            wrapper.ownData().direction = Direction.LEFT;
            if (previousAmount + (Math.abs(amount)) >= 0) {
                moving = true;
                previousAmount = 1.5f * amount;
            }
        } else if (amount > 0.045f) {
            wrapper.ownData().direction = Direction.RIGHT;
            if ((amount - previousAmount) >= 0) {
                moving = true;
                previousAmount = 1.5f * amount;
            }
        } else {
            amount = 0;
            wrapper.ownData().direction = Direction.NONE;
            moving = false;
        }
        wrapper.ownData().position = position;
    }
}
