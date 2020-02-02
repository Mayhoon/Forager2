package com.mygdx.player;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.camera.Camera;
import com.mygdx.camera.PlayerInputProcessor;
import com.mygdx.config.Paths;

public class Player {
    InputProcessor playerInputProcessor;
    Texture tex;

    public Player(Camera camera) {
        playerInputProcessor = new PlayerInputProcessor(camera);
        tex = new Texture(Paths.PLAYER_PATH);
    }

    public void render(SpriteBatch batch) {

        //batch.draw(tex, 500, 500);
    }

    public InputProcessor getProcessor() {
        return playerInputProcessor;
    }
}
