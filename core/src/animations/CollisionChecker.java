package animations;

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
    private Vector2 opponent_inner_attack, opponent_outer_attack, player_inner_attack, player_outer_attack;

    public CollisionChecker(SpriteBatch batch, Animator animator, ServerClientWrapper wrapper) {
        this.batch = batch;
        this.animator = animator;
        this.wrapper = wrapper;
        shapeDrawer();
        collisions = new Collisions();
    }

    public void update() {
        checkAttack();
    }

    public void checkAttack() {
        int attackFrameLength = collisions.getAttackHitboxes(
                wrapper.ownData().animation,
                wrapper.ownData().keyFrameIndex).length;

        for (int i = 0; i < attackFrameLength; i += 2) {
            player_inner_attack = collisions.attackHitPoint(i, wrapper.ownData());
            player_outer_attack = collisions.attackHitPoint(i + 1, wrapper.ownData());
            bluePen.line(player_inner_attack, player_outer_attack);

            opponent_inner_attack = collisions.attackHitPoint(i, wrapper.opponentData());
            opponent_outer_attack = collisions.attackHitPoint(i + 1, wrapper.opponentData());
            bluePen.line(opponent_inner_attack, opponent_outer_attack);

            checkBody();
        }
    }

    public void checkBody() {
        int bodyFrameLength = collisions.getBodyHitboxes(
                wrapper.ownData().animation,
                wrapper.ownData().keyFrameIndex).length;

        for (int i = 0; i < bodyFrameLength; i += 2) {
            Vector2 player_inner_body = collisions.bodyHitPoint(i, wrapper.ownData());
            Vector2 player_outer_body = collisions.bodyHitPoint(i + 1, wrapper.ownData());
            greenPen.line(player_inner_body, player_outer_body);

            Vector2 opponent_inner_body = collisions.bodyHitPoint(i, wrapper.opponentData());
            Vector2 opponent_outer_body = collisions.bodyHitPoint(i + 1, wrapper.opponentData());
            greenPen.line(opponent_inner_body, opponent_outer_body);

            if (wrapper.ownData().direction.equals(Direction.RIGHT)
                    && player_outer_attack.x >= opponent_inner_body.x) {
                if (player_inner_attack.x <= opponent_outer_body.x) {
                    System.out.println("Collision");
                }
            } else if (wrapper.ownData().direction.equals(Direction.LEFT)
                    && player_outer_attack.x <= opponent_outer_body.x) {
                if (player_inner_attack.x >= opponent_inner_body.x) {
                    System.out.println("Collision");
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
