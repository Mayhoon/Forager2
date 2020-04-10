package com.mygdx.entities.player;

import Enums.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.camera.Camera;
import com.mygdx.networking.ServerClientWrapper;

public class Player extends PlayerController {
    public Vector3 position;

    public Player(Entity entity, ServerClientWrapper serverClientWrapper, Camera camera) {
        super(entity, serverClientWrapper, camera);
        position = new Vector3(0f, 0f, 0f);
    }

    public void render(SpriteBatch batch) {
        update(this, batch);
    }
}
