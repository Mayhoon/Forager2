package player;

import Enums.Entity;
import camera.Camera;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import networking.ServerClientWrapper;

public class Player {
    public Vector3 position;
    private GamePadInput gamePadInput;

    public Player(Entity entity, ServerClientWrapper serverClientWrapper, Camera camera) {
        gamePadInput = new GamePadInput(entity, serverClientWrapper, camera);
        position = new Vector3(0f, 0f, 0f);
    }

    public void render(SpriteBatch batch) {
        gamePadInput.update(this, batch);
    }
}
