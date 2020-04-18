package player;

import Enums.Buttons;
import Enums.Direction;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;

public class GamePadInput implements ControllerListener {
    private Player player;

    public GamePadInput(Player player) {
        this.player = player;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        float amount = controller.getAxis(1);
        if(amount < -0.045f) {
            player.moveX(amount, Direction.LEFT);
        }else if (amount > 0.045f) {
            player.moveX(amount, Direction.RIGHT);
        }else {
            player.moveX(0, Direction.NONE);
        }
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
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
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
                break;
            case 1:
                button = Buttons.B;
                break;
            case 2:
                button = Buttons.X;
                break;
            case 3:
                button = Buttons.Y;
                break;
            case 4:
                button = Buttons.LB;
                break;
            case 5:
                button = Buttons.RB;
                break;
            case 6:
                button = Buttons.TAB;
                break;
            case 7:
                button = Buttons.MENU;
                break;
            default:
                button = Buttons.A;
        }
        player.playerInputAnimationMapper.buttonPressed(button);
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        return false;
    }
}
