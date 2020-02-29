package com.mygdx.networking;

import com.badlogic.gdx.utils.Json;
import com.mygdx.config.Resources;

import java.io.File;

public class NetworkData {
    public float ownPositionX = 0f;
    public float otherPositionX = 0f;

    public float ownPositionY = 0f;
    public float otherPositionY = 0f;

   // private static Json playerJSON;

    public NetworkData() {
//        playerJSON = new Json();
//        File file = new File(Resources.JSON_PLAYER_POSITION);
    }

}
