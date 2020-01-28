package com.mygdx.player;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.camera.Camera;
import com.mygdx.camera.PlayerInputProcessor;
import com.mygdx.config.CFG;

public class Player {
    InputProcessor playerInputProcessor;
    Texture tex;

    public Player(Camera camera) {
        playerInputProcessor = new PlayerInputProcessor(camera);
        tex = new Texture(CFG.PLAYER_PATH);
    }

    public void update(SpriteBatch batch) {
        batch.draw(tex, 0, 0);
    }

    public InputProcessor getProcessor() {
        return playerInputProcessor;
    }
}
