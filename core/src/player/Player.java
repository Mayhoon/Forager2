package player;

import Enums.Direction;
import Enums.Entity;
import animations.HitBox;
import animations.PlayerInputAnimationMapper;
import camera.Camera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import networking.NetworkData;
import networking.ServerClientWrapper;

public class Player {
    public Entity entity;
    private ServerClientWrapper wrapper;
    private NetworkData self;
    private NetworkData opponent;
    private Camera camera;
    private GamePadInput gamePadInput;
    public PlayerInputAnimationMapper playerInputAnimationMapper;
    private Boolean moving;
    private float currentControllerInput;
    private HitBox hitBox;

    public Player(Entity entity, ServerClientWrapper wrapper, Camera camera) {
        this.entity = entity;
        this.wrapper = wrapper;
        this.self = wrapper.ownData();
        this.opponent = wrapper.opponentData();
        this.camera = camera;
        this.moving = false;
        this.currentControllerInput = 0f;
        this.playerInputAnimationMapper = new PlayerInputAnimationMapper(wrapper, entity);
        this.gamePadInput = new GamePadInput(this);
        this.hitBox = new HitBox();
    }

    public void render(SpriteBatch batch) {
        if (self.moving == true && wrapper.ownData().direction.equals(Direction.LEFT)) {
            self.position.x += self.movementSpeed * Gdx.graphics.getDeltaTime();
            playerInputAnimationMapper.update(batch);
        } else if (self.moving == true && self.direction.equals(Direction.RIGHT)) {
            self.position.x += self.movementSpeed * Gdx.graphics.getDeltaTime();
            playerInputAnimationMapper.update(batch);
        } else {
            playerInputAnimationMapper.update(batch);
        }


        camera.move(self.position);
        wrapper.sendTCP();
    }

    public void moveX(float amount) {
        //Find out to which direction the player faces
        if (amount < -0.045f) {
            self.direction = Direction.LEFT;
            if (currentControllerInput + (Math.abs(amount)) >= 0) {
                self.moving = true;
                self.movementSpeed = 50.5f * amount;
            }
        } else if (amount > 0.045f) {
            self.direction = Direction.RIGHT;
            if ((amount - currentControllerInput) >= 0) {
                self.moving = true;
                self.movementSpeed = 50.5f * amount;
            }
        } else {
            amount = 0;
            self.direction = Direction.NONE;
            self.moving = false;
        }
    }
}
