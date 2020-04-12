package player;

import Enums.Button;
import Enums.Entity;
import animations.AnimationStates;
import camera.Camera;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import networking.ServerClientWrapper;

public class GamePadInput implements ControllerListener {
    private Camera camera;
    private ServerClientWrapper wrapper;
    private Entity entity;
    private AnimationStates animationStates;

    public GamePadInput(Entity entity, ServerClientWrapper wrapper, Camera camera, AnimationStates animationStates) {
        this.camera = camera;
        this.wrapper = wrapper;
        this.entity = entity;
        this.animationStates = animationStates;
    }

    @Override
    public boolean axisMoved(com.badlogic.gdx.controllers.Controller controller, int axisCode, float value) {
        float inputMinimum = 0.30f;
        float x = controller.getAxis(1);
        float y = controller.getAxis(0);

        //if (x > inputMinimum || x < -inputMinimum && y < -inputMinimum || y > inputMinimum) {
        //y * (-1)
        camera.move(x, 0);
        //}
        return false;
    }

    @Override
    public boolean povMoved(com.badlogic.gdx.controllers.Controller controller, int povCode, PovDirection value) {
        return false;
    }

    @Override
    public boolean xSliderMoved(com.badlogic.gdx.controllers.Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean ySliderMoved(com.badlogic.gdx.controllers.Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean accelerometerMoved(com.badlogic.gdx.controllers.Controller controller, int accelerometerCode, Vector3 value) {
        return false;
    }

    public void connected(com.badlogic.gdx.controllers.Controller controller) {
        System.out.println("Controller connected");
    }

    public void disconnected(com.badlogic.gdx.controllers.Controller controller) {
        System.out.println("Controller disconnected");
    }

    @Override
    public boolean buttonDown(com.badlogic.gdx.controllers.Controller controller, int buttonCode) {
        Button button;
        switch (buttonCode) {
            case 0:
                button = Button.A;
                break;
            case 1:
                button = Button.B;
                break;
            case 2:
                button = Button.X;
                break;
            case 3:
                button = Button.Y;
                break;
            case 4:
                button = Button.LB;
                break;
            case 5:
                button = Button.RB;
                break;
            case 6:
                button = Button.TAB;
                break;
            case 7:
                button = Button.MENU;
                break;
            default: button = Button.A;
        }
        animationStates.buttonPressed(button);
        return false;
    }

    @Override
    public boolean buttonUp(com.badlogic.gdx.controllers.Controller controller, int buttonCode) {
        return false;
    }
}
