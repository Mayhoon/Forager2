package tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import config.Paths;

public class FpsDisplay {
    private Table table;
    private float fps;
    private GlyphLayout glyphLayout;
    private BitmapFont bitmapFont;

    public FpsDisplay() {
        bitmapFont = new FontLoader().loadFont(Paths.ITEM_COUNT_FONT, 10, Color.BLACK);
        glyphLayout = new GlyphLayout();
    }

    public void update(SpriteBatch batch) {
        fps = Gdx.graphics.getFramesPerSecond();
        glyphLayout.setText(bitmapFont, "Fps: " + fps);

        bitmapFont.draw(batch, glyphLayout, Gdx.graphics.getWidth() / 2  - glyphLayout.width, Gdx.graphics.getHeight() / 2 - glyphLayout.height);

        glyphLayout.setText(bitmapFont, "Dt: " + Gdx.graphics.getDeltaTime());
        bitmapFont.draw(batch, glyphLayout, - Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight() / 2 - glyphLayout.height);
    }
}
