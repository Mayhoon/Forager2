package com.mygdx.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class ownStage extends Stage {
    public void update(SpriteBatch batch) {
        act();
        draw();
    }
}
