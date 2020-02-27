package com.mygdx.networking;

import com.badlogic.gdx.utils.Json;
import com.mygdx.config.Resources;

import java.io.File;

public class NetworkData {
    public int ownPositionX = 0;
    public int otherPositionX = 0;

    public int ownPositionY = 0;
    public int otherPositionY = 0;

    private static Json playerJSON;

    public NetworkData() {
        playerJSON = new Json();
        File file = new File(Resources.JSON_PLAYER_POSITION);
    }

}
