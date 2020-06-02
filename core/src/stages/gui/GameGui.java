package stages.gui;

import camera.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import networking.Network;
import player.HealthBar;
import tools.FpsDisplay;

public class GameGui {
    private Camera ui_Camera;
    private FpsDisplay fpsDisplay;
    private HealthBar healtBar;
    private SpriteBatch batch;
    private Network network;

    public GameGui(SpriteBatch batch, Network network) {
        this.batch = batch;
        this.network = network;

        ui_Camera = new Camera();
        fpsDisplay = new FpsDisplay();
        healtBar = new HealthBar();
    }

    public void update(float delta) {
        batch.setProjectionMatrix(ui_Camera.combined);
        fpsDisplay.update(batch, delta);
        healtBar.update(batch, network);
    }
}
