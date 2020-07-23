package player;

import enums.Direction;
import networking.CharacterData;

public class PlayerMotor {
    private float currentControllerInput;
    private CharacterData character;

    public PlayerMotor(CharacterData character) {
        this.character = character;
        currentControllerInput = 0f;
    }

    //Update position based on
    public void calculatePosition(float delta) {
        if (character.moving == true && character.direction.equals(Direction.LEFT)) {
            character.position.x += delta * character.movementSpeed;

        } else if (character.moving == true && character.direction.equals(Direction.RIGHT)) {
            character.position.x += delta * character.movementSpeed;
        }
    }

    public void changeMoveState(float amount) {
        //Find out to which direction the player faces
        if (amount < -0.045f) {
            character.direction = Direction.LEFT;
            if (currentControllerInput + (Math.abs(amount)) >= 0) {
                character.moving = true;
                character.movementSpeed = 60.5f * amount; //Acceleration
            }
        } else if (amount > 0.045f) {
            character.direction = Direction.RIGHT;
            if ((amount - currentControllerInput) >= 0) {
                character.moving = true;
                character.movementSpeed = 60.5f * amount;//Acceleration
            }
        } else {
            amount = 0;
            character.moving = false;
        }
    }
}
