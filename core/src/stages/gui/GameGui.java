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
    private Network network;

    public GameGui(Network network) {
        this.network = network;
        ui_Camera = new Camera();
        fpsDisplay = new FpsDisplay();
        healtBar = new HealthBar();
    }

    public void update(SpriteBatch batch, float delta) {
        batch.setProjectionMatrix(ui_Camera.combined);
        fpsDisplay.update(batch, delta);
        healtBar.updateOpponentHealth(batch, network.opponent().health);
        healtBar.updatePlayerHealth(batch, network.player().health);
    }
}
