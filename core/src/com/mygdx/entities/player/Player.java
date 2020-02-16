package com.mygdx.entities.player;

import animations.AnimationLoader;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.camera.Camera;
import com.mygdx.config.Paths;

public class Player extends PlayerInput {
    private Camera camera;
    private AnimationLoader animationLoader;

    public Player(Camera camera) {
        super(camera);
        this.camera = camera;
        animationLoader = new AnimationLoader(1.4f, Paths.PLAYER_ANIMATIONS, 6, 1);
    }

    public void render(SpriteBatch batch) {
        animationLoader.update(batch);
        update();
    }
}
