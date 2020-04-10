package com.mygdx.entities.player;

import Enums.AnimationState;
import Enums.Entity;
import animations.AnimationHandler;
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
    private Entity entity;

    public PlayerController(Entity entity, ServerClientWrapper wrapper, Camera camera) {
        this.camera = camera;
        this.wrapper = wrapper;
        this.entity = entity;
        animationHandler = new AnimationHandler(Paths.PLAYER_ANIMATION, 8 , 10);
        animationHandler.addAnimation(AnimationState.IDLE_SWORD_NOT_DRAWN, 0.1f, 0, 0, 4);
        animationHandler.addAnimation(AnimationState.IDLE_SWORD_DRAWN, 0.05f, 1, 0, 4);
        animationHandler.setAnimation(AnimationState.IDLE_SWORD_NOT_DRAWN);
    }

    public void update(Player player, SpriteBatch batch) {
        if (entity.equals(Entity.Opponent)) {
            player.position.x = wrapper.getNetworkData().otherPositionX;
            //player.position.y = wrapper.getNetworkData().otherPositionY;
            wrapper.sendTCP();
        } else {
            player.position = camera.position;
            wrapper.setPosition(player.position);
        }

        animationHandler.update(player.position, batch);
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
