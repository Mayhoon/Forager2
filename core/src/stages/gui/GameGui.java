package stages.gui;

import camera.Camera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import networking.ServerClientWrapper;
import player.HealthBar;
import tools.FpsDisplay;

import java.lang.annotation.Target;

public class GameGui {
    private Camera ui_Camera;
    private FpsDisplay fpsDisplay;
    private HealthBar healtBar;
    private SpriteBatch batch;

    public GameGui(ServerClientWrapper wrapper, SpriteBatch batch) {
        this.batch = batch;
        ui_Camera = new Camera();
        fpsDisplay = new FpsDisplay();
        healtBar = new HealthBar(wrapper);
    }

    public void update() {
        batch.setProjectionMatrix(ui_Camera.combined);
        fpsDisplay.update(batch);
        healtBar.update(batch);
    }
}
