package player;

import Enums.Direction;
import com.badlogic.gdx.Gdx;
import networking.NetworkData;
import networking.ServerClientWrapper;

public class PlayerMotor {
    private NetworkData self;
    private float currentControllerInput;

    public PlayerMotor(ServerClientWrapper wrapper) {
        self = wrapper.ownData();
        currentControllerInput = 0f;
    }

    public void render() {
        if (self.moving == true && self.direction.equals(Direction.LEFT)) {
            self.position.x += self.movementSpeed * Gdx.graphics.getDeltaTime();
        } else if (self.moving == true && self.direction.equals(Direction.RIGHT)) {
            self.position.x += self.movementSpeed * Gdx.graphics.getDeltaTime();
        }
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
            self.moving = false;
        }
    }
}
