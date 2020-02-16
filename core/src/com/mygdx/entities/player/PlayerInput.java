package com.mygdx.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;
import com.mygdx.camera.Camera;

public class PlayerInput extends ControllerAdapter {
    Camera camera;

    public PlayerInput(Camera camera) {
        this.camera = camera;
    }

    public void update() {
        float speed = 1.2f;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.moveWithKeyboard(0, speed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.moveWithKeyboard(-speed, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            camera.moveWithKeyboard(speed, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            camera.moveWithKeyboard(0, -speed);
        }
    }

    public boolean axisMoved(Controller controller, int axisCode, float value) {
        float inputMinimum = 0.30f;
        float x = controller.getAxis(1);
        float y = controller.getAxis(0);

        if (x > inputMinimum || x < -inputMinimum && y < -inputMinimum || y > inputMinimum) {
            camera.moveByController(x, y * (-1));
        }
        //System.out.println("X: " + x + " Y: " + y);
        return false;
    }

    public void connected(Controller controller) {
        System.out.println("Controller connected");
    }

    public void disconnected(Controller controller) {
        System.out.println("Controller disconnected");
    }
}
