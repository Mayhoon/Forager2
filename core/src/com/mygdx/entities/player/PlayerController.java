package com.mygdx.entities.player;

import Enums.PlayerState;
import animations.AnimationHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.camera.Camera;
import com.mygdx.config.Resources;
import com.mygdx.networking.Client;
import com.mygdx.networking.Server;
import com.mygdx.networking.ServerClientWrapper;

public class PlayerController extends ControllerAdapter {
    Camera camera;
    public boolean isPuppet;
    private AnimationHandler animationHandler;
    private ServerClientWrapper serverClientWrapper;
    private float speed = 1.2f;

    public PlayerController(boolean isPuppet, ServerClientWrapper serverClientWrapper, Camera camera) {
        this.camera = camera;
        animationHandler = new AnimationHandler(0.1f, Resources.PLAYER_RUN, 4, 1);
        this.serverClientWrapper = serverClientWrapper;
        this.isPuppet = isPuppet;
    }

    public void update(Player player, SpriteBatch batch) {
        if (!isPuppet) {
            player.position = camera.position;
            serverClientWrapper.sendPosition(player.position);

        } else {
            player.position.x = serverClientWrapper.getOpponentPositionX();
            player.position.y = serverClientWrapper.getOpponentPositionY();
        }
        animationHandler.update(player.position, PlayerState.IDLE, batch);

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
//      Logger.log("X: " + x + " Y: " + y);
        return false;
    }

    public void connected(Controller controller) {
        System.out.println("Controller connected");
    }

    public void disconnected(Controller controller) {
        System.out.println("Controller disconnected");
    }
}
