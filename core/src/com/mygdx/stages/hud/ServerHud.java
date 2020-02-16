package com.mygdx.stages.hud;

import com.mygdx.networking.Server;
import com.mygdx.stages.ownStage;

public class ServerHud extends ownStage {

    public void startServer() {
        Thread serverThread = new Thread(new Server());
        serverThread.start();
    }
}
