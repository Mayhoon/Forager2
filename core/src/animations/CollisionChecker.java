package animations;

import Enums.AnimationName;
import collision.AttackHitBox;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import networking.ServerClientWrapper;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.util.HashMap;
import java.util.Map;

public class CollisionChecker {
    private Map animations;
    private TextureRegion region;
    private ShapeDrawer shapeDrawer;
    private SpriteBatch batch;
    private ServerClientWrapper wrapper;
    private Animator animator;
    private AttackHitBox attackHitBox;

    public CollisionChecker(SpriteBatch batch, Animator animator, ServerClientWrapper wrapper) {
        this.batch = batch;
        this.animator = animator;
        this.wrapper = wrapper;
        shapeDrawer();
        attackHitBox = new AttackHitBox();
    }

    public void drawHitboxes() {
        Vector2 list[] = attackHitBox.getAttackHitboxes(wrapper.ownData().animation, wrapper.ownData().keyFrameIndex);

        float playerX = wrapper.ownData().position.x;
        float playerY = wrapper.ownData().position.y;

        for (int i = 0; i < list.length; i+=2) {
            shapeDrawer.line(list[i].x + playerX, list[i].y + playerY, list[i+1].x + playerX, list[i+1].y + playerY);
        }
    }

    private void shapeDrawer() {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLUE);
        pixmap.drawPixel(0, 0);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        region = new TextureRegion(texture, 0, 0, 1, 1);
        shapeDrawer = new ShapeDrawer(batch, region);
    }
}
