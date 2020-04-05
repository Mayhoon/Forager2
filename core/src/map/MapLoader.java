package map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MapLoader {
    public void load(MapRegion region) {
        TiledMap tiledMap = new TmxMapLoader().load(region + ".tmx");

    }
}
