package player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import config.Paths;
import networking.ServerClientWrapper;
import tools.FontLoader;

public class HealthBar {
    private float health;
    private BitmapFont bitmapFont;
    private GlyphLayout glyphLayout;
    private ServerClientWrapper wrapper;

    public HealthBar(ServerClientWrapper wrapper) {
        this.wrapper = wrapper;
        this.health = 100f;

        bitmapFont = new FontLoader().loadFont(Paths.ITEM_COUNT_FONT, 10, Color.BLACK);
        glyphLayout = new GlyphLayout();
    }

    public void update(SpriteBatch batch) {
        glyphLayout.setText(bitmapFont, "OWN HEALTH: " + wrapper.ownData().health);
        bitmapFont.draw(batch, glyphLayout, glyphLayout.width, -100 - glyphLayout.height);

        glyphLayout.setText(bitmapFont, "OPPONENT HEALTH: " + wrapper.opponentData().health);
        bitmapFont.draw(batch, glyphLayout, -glyphLayout.width, -100 - glyphLayout.height);
    }


}
