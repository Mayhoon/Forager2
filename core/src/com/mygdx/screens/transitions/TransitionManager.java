package com.mygdx.screens.transitions;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.stages.ownStage;

public class TransitionManager {

    private void fadeOut(ownStage currentStage) {
        currentStage.addAction(Actions.sequence(Actions.fadeOut(0.15f)));
    }

    private void fadeIn(ownStage nextStage) {
        nextStage.addAction(Actions.sequence(Actions.fadeIn(0.15f)));
    }

    public void changeStage(ownStage currentStage, ownStage nextStage) {
        fadeOut(currentStage);
        fadeIn(nextStage);
    }
}
