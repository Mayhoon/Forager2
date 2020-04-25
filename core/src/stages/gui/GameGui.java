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

    public GameGui(ServerClientWrapper wrapper) {
        Table table = new Table();
        table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        ui_Camera = new Camera();
        fpsDisplay = new FpsDisplay();
        healtBar = new HealthBar(wrapper, table);
    }

    public void update(SpriteBatch batch) {
        batch.setProjectionMatrix(ui_Camera.combined);
        fpsDisplay.update(batch);
        healtBar.update(batch);
    }
}
