package player;

import Enums.Direction;
import Enums.Entity;
import animations.AnimationHandler;
import camera.Camera;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import networking.ServerClientWrapper;

public class Player {
    private Entity entity;
    private ServerClientWrapper wrapper;
    private Camera camera;
    public Vector2 position;
    private GamePadInput gamePadInput;
    public AnimationHandler animationHandler;
    private Boolean moving;
    float tmp;
    private Direction direction;

    public Player(Entity entity, ServerClientWrapper wrapper, Camera camera) {
        this.entity = entity;
        this.wrapper = wrapper;
        this.camera = camera;
        this.moving = false;
        this.tmp = 0f;
        this.direction = Direction.RIGHT;

        position = new Vector2(0, 0);
        animationHandler = new AnimationHandler(wrapper, entity);

        if (entity.equals(Entity.Player)) {
            gamePadInput = new GamePadInput(this);
            Controllers.addListener(gamePadInput);
        }
    }

    public void render(SpriteBatch batch) {
        if (moving == true && direction.equals(Direction.LEFT)) {
            position.x += 1.5f * tmp;
            animationHandler.update(batch, position);
        } else if (moving == true && direction.equals(Direction.RIGHT)) {
            position.x += 1.5f * tmp;
            animationHandler.update(batch, position);
        } else {
            animationHandler.update(batch, position);
        }

//        if (entity.equals(Entity.Opponent)) {
//            position = wrapper.ownData().position;
//            wrapper.sendTCP();
//        } else {
//            camera.position.x = position.x;
//            camera.position.y = position.y;
//        }
        wrapper.sendTCP();
    }

    public void moveX(float amount, Direction direction) {
        if (direction.equals(Direction.LEFT)) {
            if (tmp + (Math.abs(amount)) >= 0) {
                this.direction = direction;
                moving = true;
                tmp = amount;
            }
        } else if (direction.equals(Direction.RIGHT)) {
            if ((amount - tmp) >= 0) {
                this.direction = direction;
                moving = true;
                tmp = amount;
            }
        } else if (direction.equals(Direction.NONE)) {
            System.out.println("Not moving");
            moving = false;
        }

        System.out.println(amount);
        wrapper.ownData().position = position;
    }
}
