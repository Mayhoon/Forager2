package animations;

import Enums.AnimationName;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.util.Map;

public class HitBox {
    private Map animations;
    private int size = 2;
    private Vector2[][] range = new Vector2[size][2];
    private TextureRegion region;
    private ShapeDrawer shapeDrawer;
    private SpriteBatch batch;

    public HitBox(SpriteBatch batch) {
        this.batch = batch;
        shapeDrawer();
        range[0][0] = new Vector2(5, 9);
        range[0][1] = new Vector2(12, 9);

        range[1][0] = new Vector2(14, 0);
        range[1][1] = new Vector2(21, 0);
    }

    public void getHitPoints(AnimationName animationName, int frameIndex) {

    }

    public void drawHitboxes(AnimationName animationName, int frameIndex) {
        System.out.println(animationName + "---" + frameIndex);
        if (animationName.equals(AnimationName.SWORD_SLASH_UP_DOWN_STANDING) && frameIndex == 3) {
            for (int i = 0; i < size; i++) {
                float width = range[i][1].x - range[i][0].x;
                float height = 0;
                shapeDrawer.rectangle(range[i][0].x, range[i][0].y, width, height);
            }
        }
    }

    private void shapeDrawer() {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.drawPixel(0, 0);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        region = new TextureRegion(texture, 0, 0, 1, 1);
        shapeDrawer = new ShapeDrawer(batch, region);
    }

}
