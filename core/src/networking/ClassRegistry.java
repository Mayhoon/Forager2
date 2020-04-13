package networking;

import Enums.AnimationState;
import animations.Animations;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.badlogic.gdx.backends.lwjgl.LwjglFileHandle;

import java.io.File;

public class ClassRegistry {

    public Kryo addClassesTo(Kryo kryo) {
        kryo.register(NetworkData.class);
        kryo.register(TextureRegion.class);

        //Animations
        kryo.register(Vector2.class);
        kryo.register(AnimationState.class);

//        kryo.register(Animations.class);
//        kryo.register(Texture.class);
//        kryo.register(FileTextureData.class);
//        kryo.register(FileHandle.class);
//        kryo.register(LwjglFileHandle.class);
//        kryo.register(File.class);
//        kryo.register(Files.FileType.class);
//        kryo.register(Pixmap.class);

        return kryo;
    }
}
