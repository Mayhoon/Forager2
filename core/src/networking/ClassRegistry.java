package networking;

import Enums.AnimationState;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;

public class ClassRegistry {

    public Kryo addClassesTo(Kryo kryo) {
        kryo.register(NetworkData.class);

        //Animations
        kryo.register(Vector2.class);
        kryo.register(AnimationState.class);

        return kryo;
    }
}
