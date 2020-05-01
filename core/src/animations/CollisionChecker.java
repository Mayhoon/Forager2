package animations;

import Enums.AnimationName;
import Enums.Direction;
import collision.Collisions;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import networking.ServerClientWrapper;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.util.Map;

public class CollisionChecker {
    private SpriteBatch batch;
    private ServerClientWrapper wrapper;
    private ShapeDrawer bluePen, greenPen;
    private Collisions collisions;
    private Animator animator;
    private Map animations;

    public CollisionChecker(SpriteBatch batch, Animator animator, ServerClientWrapper wrapper) {
        this.batch = batch;
        this.animator = animator;
        this.wrapper = wrapper;
        shapeDrawer();
        collisions = new Collisions(wrapper);
    }

    public void checkAttack() {
        Direction direction = wrapper.ownData().direction;
        AnimationName animationName = wrapper.ownData().animation;
        int keyFrameIndex = wrapper.ownData().keyFrameIndex;

        try {
            Vector2 attackFrame[] = collisions.getAttackHitboxes(wrapper.ownData().animation, wrapper.ownData().keyFrameIndex);
            Vector2 player = wrapper.ownData().position;

            for (int i = 0; i < attackFrame.length; i += 2) {
                Vector2 leftHitPoint = collisions.hitPoint(
                        player,
                        i,
                        animationName,
                        direction,
                        keyFrameIndex);
                Vector2 rightHitPoint = collisions.hitPoint(
                        player,
                        i + 1,
                        animationName,
                        direction,
                        keyFrameIndex);

                bluePen.line(leftHitPoint, rightHitPoint);
            }
        } catch (Exception e) {

        }
    }

//    public void checkBody() {
//        Vector2 bodyFrame[] = collisions.getBodyHitboxes(wrapper.ownData().animation, wrapper.ownData().keyFrameIndex);
//
//        for (int i = 0; i < bodyFrame.length; i += 2) {
//            if (wrapper.ownData().direction.equals(Direction.LEFT)) {
//
//                float x = (player.x + 64) - bodyFrame[i].x;
//                float y1 = bodyFrame[i].y + player.y;
//                float x2 = (player.x + 64) - bodyFrame[i + 1].x;
//                float y2 = bodyFrame[i + 1].y + player.y;
//                greenPen.line(x, y1, x2, y2);
//
//            } else if (wrapper.ownData().direction.equals(Direction.RIGHT)) {
//                greenPen.line(
//                        player.x + bodyFrame[i].x,
//                        bodyFrame[i].y + player.y,
//                        player.x + bodyFrame[i + 1].x,
//                        bodyFrame[i + 1].y + player.y);
//            }
//        }
//    }

    private void shapeDrawer() {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLUE);
        pixmap.drawPixel(0, 0);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        TextureRegion blueRegion = new TextureRegion(texture, 0, 0, 1, 1);
        bluePen = new ShapeDrawer(batch, blueRegion);

        pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.drawPixel(0, 0);
        texture = new Texture(pixmap);
        pixmap.dispose();
        TextureRegion greenRegion = new TextureRegion(texture, 0, 0, 1, 1);
        greenPen = new ShapeDrawer(batch, greenRegion);
    }
}
