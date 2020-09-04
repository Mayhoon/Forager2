package collision;

import collision.bodyparts.BodyPart;
import collision.bodyparts.Head;
import collision.bodyparts.Legs;
import collision.bodyparts.PlayerBody;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.gushikustudios.rube.RubeScene;
import enums.AnimationName;
import enums.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SingleAnimationFrameCollider {
    List<BodyPart> collider;
    AnimationName animationName;
    Direction direction;
    RubeScene scene;
    int animationFrame;

    public SingleAnimationFrameCollider(RubeScene scene, AnimationName animationName, Direction direction, int animationFrame) {
        this.scene = scene;
        this.animationName = animationName;
        this.direction = direction;
        this.animationFrame = animationFrame;
        collider = new ArrayList<BodyPart>();

        /*
        Assuming that the standard animation consists of
        3 parts: head, body and legs. Can easily be expanded when needed.
        */
        addHeadCollider();
        addBodyCollider();
        addLegCollider();
    }

    public void addHeadCollider() {
        String rubeAnimationName = animationFrame + "_HEAD_" + animationName + "_" + direction;
        System.out.println(rubeAnimationName);
        Head head = new Head(animationName, scene.getNamed(Body.class, rubeAnimationName).get(0), animationFrame, direction, 3.1f, 2.1f);
        collider.add(head);
    }

    public void addBodyCollider() {
        String rubeAnimationName = animationFrame + "_BODY_" + animationName + "_" + direction;
        PlayerBody body = new PlayerBody(animationName, scene.getNamed(Body.class, rubeAnimationName).get(0), animationFrame, direction, 3.15f, 1.4f);
        collider.add(body);
    }

    public void addLegCollider() {
        String rubeAnimationName = animationFrame + "_LEGS_" + animationName + "_" + direction;
        Legs legs = new Legs(animationName, scene.getNamed(Body.class, rubeAnimationName).get(0), animationFrame, direction, 3.2f, 0.4f);
        collider.add(legs);
    }

    //Iterate over the parts that represent a single animation frame
    public void updatePosition(Vector2 position, AnimationName animationName, int frameIndex, Direction direction) {
        for (BodyPart frameCollider : collider) {
            frameCollider.updatePosition(position, animationName, frameIndex, direction);
        }
    }

    public void deactivate() {
        for (BodyPart frameCollider : collider) {
            frameCollider.deactivate();
        }
    }
}
