package com.mygdx.entities.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.camera.Camera;
import com.mygdx.networking.ServerClientWrapper;

public class Player extends PlayerController {
    public Vector3 position;

    public Player(boolean isPuppet, ServerClientWrapper serverClientWrapper, Camera camera) {
        super(isPuppet, serverClientWrapper, camera);
        position = new Vector3(30, 30, 0);
    }

    public void render(SpriteBatch batch) {
        update(this, batch);
    }
}
