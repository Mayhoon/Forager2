package collision;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class PlayerHead {

    public String id;

    public PlayerHead(Body body, String id) {
        this.id = id;
        setUserData(body);
    }

    private void setUserData(Body body) {
        body.setUserData(this);
    }

    public void hit() {
        System.out.println("[" + id + "] got hit.");
    }
}
