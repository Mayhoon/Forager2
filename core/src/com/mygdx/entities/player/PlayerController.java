package com.mygdx.entities.player;

import Enums.AnimationState;
import animations.AnimationHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.camera.Camera;
import com.mygdx.config.Paths;
import com.mygdx.networking.ServerClientWrapper;

public class PlayerController extends ControllerAdapter {
    private Camera camera;
    private AnimationHandler animationHandler;
    private ServerClientWrapper wrapper;
    public boolean isPuppet;

    public PlayerController(boolean isPuppet, ServerClientWrapper wrapper, Camera camera) {
        this.camera = camera;
        this.wrapper = wrapper;
        this.isPuppet = isPuppet;
        animationHandler = new AnimationHandler(Paths.PLAYER_ANIMATION, 4, 2);
        animationHandler.addAnimation(AnimationState.IDLE, 0.05f, 0, 0, 4);
        animationHandler.addAnimation(AnimationState.MOVING, 0.1f, 1, 0, 4);
        animationHandler.setAnimation(AnimationState.IDLE);
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

        animationHandler.update(player.position, batch);

        float speed = 0.5f;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.moveWithKeyboard(0, speed);
            animationHandler.setAnimation(AnimationState.MOVING);
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
        wrapper.sendTCP();
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
