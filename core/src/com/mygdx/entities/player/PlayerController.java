package com.mygdx.entities.player;

import Enums.PlayerState;
import animations.AnimationHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.camera.Camera;
import com.mygdx.config.Resources;
import com.mygdx.networking.ServerClientWrapper;

public class PlayerController extends ControllerAdapter {
    private Camera camera;
    public boolean isPuppet;
    private AnimationHandler animationHandler;
    private ServerClientWrapper wrapper;

    public PlayerController(boolean isPuppet, ServerClientWrapper wrapper, Camera camera) {
        this.camera = camera;
        animationHandler = new AnimationHandler(0.1f, Resources.PLAYER_RUN, 4, 1);
        this.wrapper = wrapper;
        this.isPuppet = isPuppet;
    }

    public void update(Player player, SpriteBatch batch) {
        if (isPuppet) {
            player.position.x = wrapper.getNetworkData().otherPositionX;
            player.position.y = wrapper.getNetworkData().otherPositionY;
            wrapper.sendTCP();

        } else {
            player.position = camera.position;
            wrapper.setPosition(player.position);
        }
        animationHandler.update(player.position, PlayerState.IDLE, batch);

        float speed = 0.8f;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            wrapper.sendTCP();
            camera.moveWithKeyboard(0, speed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            wrapper.sendTCP();
            camera.moveWithKeyboard(-speed, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            wrapper.sendTCP();
            camera.moveWithKeyboard(speed, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            wrapper.sendTCP();
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
        return false;
    }

    public void connected(Controller controller) {
        System.out.println("Controller connected");
    }

    public void disconnected(Controller controller) {
        System.out.println("Controller disconnected");
    }
}
