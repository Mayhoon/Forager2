package com.mygdx.stages.transitions;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.stages.hud.ClientHud;
import com.mygdx.stages.ownStage;

public class StageManager {
    private ownStage currentStage;

    public StageManager(ownStage currentStage){
        this.currentStage = currentStage;
    }

    public void changeStage(final ownStage nextStage) {
        currentStage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
            @Override
            public void run() {
                System.out.println("Action complete!");
                currentStage.dispose();
                currentStage = nextStage;
            }
        })));
    }

    public ownStage getCurrentStage(){
        return currentStage;
    }

    public void updateCurrentStage(SpriteBatch batch) {
        currentStage.update(batch);
    }
}
