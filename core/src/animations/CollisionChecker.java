package animations;

import Enums.Direction;
import collision.Collisions;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import networking.CharacterData;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.util.Map;

public class CollisionChecker {
    private SpriteBatch batch;
    private ShapeDrawer bluePen, greenPen;
    private Collisions collisions;
    private Map animations;
    private Vector2 opponent_inner_attack;
    private Vector2 opponent_outer_attack;
    private Vector2 player_inner_attack;
    private Vector2 player_outer_attack;
    private Vector2 player_inner_body;
    private Vector2 player_outer_body;
    private Vector2 opponent_inner_body;
    private Vector2 opponent_outer_body;
    private CharacterData player;
    private CharacterData opponent;

    public CollisionChecker(SpriteBatch batch, CharacterData player, CharacterData opponent) {
        this.player = player;
        this.opponent = opponent;
        this.batch = batch;
        collisions = new Collisions();
        shapeDrawer();
    }

    public void attackCollisionPoints() {
        int attackFrameLength = collisions.getAttackHitboxes(
                player.animation,
                player.keyFrameIndex).length;

        for (int i = 0; i < attackFrameLength; i += 2) {
            player_inner_attack = collisions.attackHitPoint(i, player);
            player_outer_attack = collisions.attackHitPoint(i + 1, player);
            opponent_inner_attack = collisions.attackHitPoint(i, opponent);
            opponent_outer_attack = collisions.attackHitPoint(i + 1,opponent);
            bodyCollisionsPoints();
        }
    }

    private void bodyCollisionsPoints() {
        int bodyHitpoints = collisions.getBodyHitboxes(player.animation, player.keyFrameIndex).length;

        for (int i = 0; i < bodyHitpoints; i += 2) {
            player_inner_body = collisions.bodyHitPoint(i, player);
            player_outer_body = collisions.bodyHitPoint(i + 1, player);
            opponent_inner_body = collisions.bodyHitPoint(i, opponent);
            opponent_outer_body = collisions.bodyHitPoint(i + 1, opponent);
            drawHitPoints();

            checkForCollision();
        }
    }

    private void checkForCollision() {
        if (player.direction.equals(Direction.RIGHT)
                && player_outer_attack.x >= opponent_inner_body.x) {
            if (player_inner_attack.x <= opponent_outer_body.x) {
                System.out.println("Collision");
            }
        } else if (player.direction.equals(Direction.LEFT)
                && player_outer_attack.x <= opponent_outer_body.x) {
            if (player_inner_attack.x >= opponent_inner_body.x) {
                System.out.println("Collision");
            }
        }
    }

    private void drawHitPoints() {
        //Body hitpoints
        greenPen.line(player_inner_body, player_outer_body);
        greenPen.line(opponent_inner_body, opponent_outer_body);

        //Attack hitpoints
        bluePen.line(player_inner_attack, player_outer_attack);
        bluePen.line(opponent_inner_attack, opponent_outer_attack);
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
