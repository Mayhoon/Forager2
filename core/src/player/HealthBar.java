package player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import config.Paths;
import networking.Network;
import tools.FontLoader;

public class HealthBar {
    private BitmapFont bitmapFont;
    private GlyphLayout glyphLayout;

    public HealthBar() {
        bitmapFont = new FontLoader().loadFont(Paths.ITEM_COUNT_FONT, 10, Color.BLACK);
        glyphLayout = new GlyphLayout();
    }

    public void update(SpriteBatch batch, Network network) {
        glyphLayout.setText(bitmapFont, "OWN HEALTH: " + network.player().health);
        bitmapFont.draw(batch, glyphLayout, glyphLayout.width, -100 - glyphLayout.height);

        glyphLayout.setText(bitmapFont, "OPPONENT HEALTH: " + network.opponent().health);
        bitmapFont.draw(batch, glyphLayout, -glyphLayout.width, -100 - glyphLayout.height);
    }


}
