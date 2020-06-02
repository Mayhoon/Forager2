package networking;

import Enums.AnimationName;
import Enums.Direction;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;

public class ClassRegistry {

    public Kryo addClassesTo(Kryo kryo) {
        kryo.register(State.class);

        //Animations
        kryo.register(Vector2.class);
        kryo.register(AnimationName.class);
        kryo.register(Direction.class);

        return kryo;
    }
}
