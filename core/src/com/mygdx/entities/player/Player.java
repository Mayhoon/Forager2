package com.mygdx.entities.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.camera.Camera;

public class Player extends PlayerController {
    private Vector2 playerPosition;

    public Player(Camera camera) {
        super(camera);
        playerPosition = new Vector2(0,0);
    }

    public void render(SpriteBatch batch) {
        update(batch);
    }
}
