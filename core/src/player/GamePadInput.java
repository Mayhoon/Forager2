package player;

import Enums.AnimationState;
import Enums.Entity;
import animations.Animations;
import camera.Camera;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import config.Paths;
import networking.ServerClientWrapper;

public class GamePadInput implements ControllerListener {
    private Camera camera;
    private Animations animations;
    private ServerClientWrapper wrapper;
    private Entity entity;

    public GamePadInput(Entity entity, ServerClientWrapper wrapper, Camera camera) {
        this.camera = camera;
        this.wrapper = wrapper;
        this.entity = entity;
        animations = new Animations(Paths.PLAYER_ANIMATION, 8 , 10);
        animations.add(AnimationState.IDLE_SWORD_NOT_DRAWN, 0.1f, 0, 0, 4);
        animations.add(AnimationState.IDLE_SWORD_DRAWN, 0.05f, 1, 0, 4);
        animations.set(AnimationState.IDLE_SWORD_NOT_DRAWN);
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

        animations.update(player.position, batch);
        wrapper.sendTCP();
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
        return false;
    }

    @Override
    public boolean buttonUp(com.badlogic.gdx.controllers.Controller controller, int buttonCode) {
        return false;
    }
}
