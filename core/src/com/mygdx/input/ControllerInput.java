package com.mygdx.input;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.camera.Camera;
import com.mygdx.player.Player;


public class ControllerInput implements ControllerListener {
    Player player;
    final Camera camera;

    public ControllerInput(final Camera camera) {
        player = new Player(camera);
        this.camera = camera;
    }

    @Override
    public void connected(Controller controller) {
        System.out.println("Controller connected");
    }

    @Override
    public void disconnected(Controller controller) {
        System.out.println("Controller disconnected");
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        System.out.println("Button down: " + buttonCode);
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        System.out.println("Button released: " + buttonCode);
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        float x = controller.getAxis(1);
        float y = controller.getAxis(0);

        camera.moveDirect(x, y*(-1));
        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        System.out.println("povMoved" + povCode + " by: " + value);
        return false;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        System.out.println("X slider moved: " + sliderCode + " by: " + value);
        return false;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        System.out.println("Y slider moved: " + sliderCode + " by: " + value);
        return false;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        System.out.println("Accelerometer moved " + accelerometerCode + " by: " + value);
        return false;
    }
}
