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
        int frameIndex = animator.getKeyFrameIndex();

        if (wrapper.ownData().animation.equals(AnimationName.SWORD_SLASH_UP_DOWN_STANDING)) {
            Vector2 list[][] = attackHitBox.getAttackHitboxes(wrapper.ownData().animation, frameIndex);

            float originX = wrapper.ownData().position.x + animator.getAnimationWidth() / 2;
            float originY = wrapper.ownData().position.y + animator.getAnimationHeight() / 2;

            for (int i = 0; i < list.length; i++) {
                float width = list[i][1].x - list[i][0].x;
                float height = 0;
                shapeDrawer.rectangle(list[i][0].x + originX, list[i][0].y + originY, width, height);

                float opponentXLeft = wrapper.opponentData().position.x;
                float opponentXRight =  animator.getAnimationWidth(wrapper.opponentData().animation) + wrapper.opponentData().position.x;
                float opponentYLeft = animator.getAnimationHeight(wrapper.opponentData().animation);
                System.out.println(opponentXLeft + "--" + opponentYLeft);

                float selfXLeft = list[i][0].x + originX;
                float selfXRight = list[i][1].x + originX;
                System.out.println(selfXLeft + "---" + selfXRight);
                if(selfXRight > opponentXLeft && selfXLeft < opponentXRight) {
                    System.out.println("COLLISION");
                }

            }
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
