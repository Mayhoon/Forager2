package player;

import enums.AnimationName;
import enums.Buttons;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import networking.CharacterData;

public class GamePadInput implements ControllerListener {
    private PlayerMotor playerMotor;
    private CharacterData character;

    public GamePadInput(PlayerMotor playerMotor, CharacterData character) {
        this.playerMotor = playerMotor;
        this.character = character;
        Controllers.addListener(this);
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        playerMotor.changeMoveState(controller.getAxis(1));
        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        return false;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int i, Vector3 vector3) {
        return false;
    }

    public void connected(Controller controller) {
        System.out.println("Controller connected");
    }

    public void disconnected(Controller controller) {
        System.out.println("Controller disconnected");
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        Buttons button;
        switch (buttonCode) {
            case 0:
                button = Buttons.A;
                character.animation = AnimationName.SWORD_SLASH_UP_DOWN;
                break;
            case 1:
                button = Buttons.B;
                break;
            case 2:
                button = Buttons.X;
                character.animation = AnimationName.DRAW_SWORD;
                break;
            case 3:
                button = Buttons.Y;
                character.animation = AnimationName.IDLE_SWORD_NOT_DRAWN;
                break;
            case 4:
                button = Buttons.LB;
                character.animation = AnimationName.IDLE_SWORD_DRAWN;
                break;
            case 5:
                button = Buttons.RB;
                character.animation = AnimationName.SWORD_SLASH_SPIN;
                break;
            case 6:
                button = Buttons.TAB;
                break;
            case 7:
                button = Buttons.MENU;
                break;
            default:
                button = Buttons.A;
                character.animation = AnimationName.IDLE_SWORD_NOT_DRAWN;
        }
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        return false;
    }
}
