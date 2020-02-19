package com.mygdx.stages;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class customStage extends Stage {
    //This spritebatch renders non actor derived classes
    private SpriteBatch batch;

    public customStage(SpriteBatch batch) {
        this.batch = batch;
    }

    public void render(SpriteBatch batch) {

    }
}
