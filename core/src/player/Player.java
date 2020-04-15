package player;

import Enums.Entity;
import animations.AnimationHandler;
import camera.Camera;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import networking.ServerClientWrapper;

public class Player {
    private Entity entity;
    private ServerClientWrapper wrapper;
    private Camera camera;
    public Vector2 position;
    private GamePadInput gamePadInput;
    private AnimationHandler animationHandler;

    public Player(Entity entity, ServerClientWrapper wrapper, Camera camera) {
        this.entity = entity;
        this.wrapper = wrapper;
        this.camera = camera;
        position = new Vector2(0, 0);
        animationHandler = new AnimationHandler(wrapper, entity);

        if(entity.equals(Entity.Player)){
            gamePadInput = new GamePadInput(entity, wrapper, camera, animationHandler);
            Controllers.addListener(gamePadInput);
        }
    }

    public void render(SpriteBatch batch) {
        animationHandler.update(batch, position);

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
