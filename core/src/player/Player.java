package player;

import Enums.Entity;
import animations.AnimationStates;
import camera.Camera;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import networking.ServerClientWrapper;

public class Player {
    private Entity entity;
    private ServerClientWrapper wrapper;
    private Camera camera;
    public Vector2 position;
    private GamePadInput gamePadInput;
    private AnimationStates animationStates;

    public Player(Entity entity, ServerClientWrapper wrapper, Camera camera) {
        this.entity = entity;
        this.wrapper = wrapper;
        this.camera = camera;
        position = new Vector2(0, 0);
        animationStates = new AnimationStates(wrapper, entity);

        if(entity.equals(Entity.Player)){
            gamePadInput = new GamePadInput(entity, wrapper, camera, animationStates);
            Controllers.addListener(gamePadInput);
        }
    }

    public void render(SpriteBatch batch) {
        animationStates.update(batch, position);

        if (entity.equals(Entity.Opponent)) {
            position = wrapper.ownData().position;
            wrapper.sendTCP();
        } else {
            position.x = camera.position.x;
            position.y = camera.position.y;
            wrapper.ownData().position = position;
        }
        wrapper.sendTCP();
    }
}
