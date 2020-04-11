package networking;

import Enums.AnimationState;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esotericsoftware.kryo.Kryo;

public class ClassRegistry {

    public Kryo addClassesTo(Kryo kryo) {
        kryo.register(NetworkData.class);
        kryo.register(TextureRegion.class);
        kryo.register(AnimationState.class);
        return kryo;
    }
}
