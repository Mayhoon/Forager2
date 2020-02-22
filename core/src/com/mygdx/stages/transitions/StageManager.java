package com.mygdx.stages.transitions;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.stages.customStage;

public class StageManager {
    private customStage currentStage;

    public StageManager(customStage currentStage){
        this.currentStage = currentStage;
    }

    public void changeStage(final customStage nextStage) {
        currentStage.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
            @Override
            public void run() {
                System.out.println("Action complete!");
                currentStage.dispose();
                currentStage = nextStage;
            }
        })));
    }

    public void updateCurrentStage(SpriteBatch batch) {
        //Render actors of the stage
        currentStage.draw();
        currentStage.act();

        //Render all non-actors
        batch.begin();
        currentStage.render(batch);
        batch.end();
    }
}
