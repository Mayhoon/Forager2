package player;

import Enums.Direction;
import Enums.Entity;
import animations.PlayerInputAnimationMapper;
import camera.Camera;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import networking.ServerClientWrapper;
import org.graalvm.compiler.lir.amd64.AMD64Arithmetic;

public class Player {
    private Entity entity;
    private ServerClientWrapper wrapper;
    private Camera camera;
    public Vector2 position;
    private GamePadInput gamePadInput;
    public PlayerInputAnimationMapper playerInputAnimationMapper;
    private Boolean moving;
    float previousAmount;
    private Direction direction;

    public Player(Entity entity, ServerClientWrapper wrapper, Camera camera) {
        this.entity = entity;
        this.wrapper = wrapper;
        this.camera = camera;
        this.moving = false;
        this.previousAmount = 0f;
        this.direction = Direction.RIGHT;
        this.position = new Vector2(0, 0);
        this.playerInputAnimationMapper = new PlayerInputAnimationMapper(wrapper, entity);
        if (entity.equals(Entity.Player)) {
            this.gamePadInput = new GamePadInput(this);
            Controllers.addListener(gamePadInput);
        }
    }

    public void render(SpriteBatch batch) {
        if (moving == true && direction.equals(Direction.LEFT)) {
            position.x += 1.5f * previousAmount;
            playerInputAnimationMapper.update(batch, position, direction);

        } else if (moving == true && direction.equals(Direction.RIGHT)) {
            position.x += 1.5f * previousAmount;
            playerInputAnimationMapper.update(batch, position, direction);

        } else {
            playerInputAnimationMapper.update(batch, position, Direction.NONE);
        }
        sendPlayerInformation();
    }

    private void sendPlayerInformation() {
        if (entity.equals(Entity.Opponent)) {
            position = wrapper.ownData().position;
        } else {
            camera.position.x = position.x;
            camera.position.y = position.y;
        }
        wrapper.sendTCP();
    }

    public void moveX(float amount) {
        //Find out to which direction the player faces
        if (amount < -0.045f) {
            direction = Direction.LEFT;
            if (previousAmount + (Math.abs(amount)) >= 0) {
                this.direction = direction;
                moving = true;
                previousAmount = amount;
            }
        } else if (amount > 0.045f) {
            direction = Direction.RIGHT;
            if ((amount - previousAmount) >= 0) {
                this.direction = direction;
                moving = true;
                previousAmount = amount;
            }
        } else {
            amount = 0;
            direction = Direction.NONE;
            moving = false;
        }
        wrapper.ownData().position = position;
    }
}
