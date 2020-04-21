package stages.gui;

import camera.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import tools.FpsDisplay;

public class GameGui {
    private Camera ui_Camera;
    private FpsDisplay fpsDisplay;

    public GameGui() {
        ui_Camera = new Camera();
        fpsDisplay = new FpsDisplay();
    }

    public void update(SpriteBatch batch) {
        batch.setProjectionMatrix(ui_Camera.combined);
        fpsDisplay.update(batch);
    }
}
