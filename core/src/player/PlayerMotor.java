package player;

import Enums.Direction;
import com.badlogic.gdx.Gdx;
import networking.State;

public class PlayerMotor {
    private float currentControllerInput;
    private State state;

    public PlayerMotor(State data) {
        this.state = data;
        currentControllerInput = 0f;
    }

    public void calculatePosition(float delta) {
        if (state.moving == true && state.direction.equals(Direction.LEFT)) {
            state.position.x += delta * state.movementSpeed;

        } else if (state.moving == true && state.direction.equals(Direction.RIGHT)) {
            state.position.x += delta * state.movementSpeed;
        }
    }

    public void changeMoveState(float amount) {
        //Find out to which direction the player faces
        if (amount < -0.045f) {
            state.direction = Direction.LEFT;
            if (currentControllerInput + (Math.abs(amount)) >= 0) {
                state.moving = true;
                state.movementSpeed = 50.5f * amount;
            }
        } else if (amount > 0.045f) {
            state.direction = Direction.RIGHT;
            if ((amount - currentControllerInput) >= 0) {
                state.moving = true;
                state.movementSpeed = 50.5f * amount;
            }
        } else {
            amount = 0;
            state.moving = false;
        }
    }
}
