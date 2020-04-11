package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import game.Main;
import stages.hud.StartmenuHud;
import stages.transitions.StageManager;

public class Startmenu extends ScreenAdapter {
    public Main game;
    public StageManager stageManager;

    public Startmenu(Main game) {
        this.game = game;
        stageManager = new StageManager(new StartmenuHud(this));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(221, 221, 221, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
            System.exit(0);
        }
         stageManager.updateCurrentStage(game.batch);
    }
}
