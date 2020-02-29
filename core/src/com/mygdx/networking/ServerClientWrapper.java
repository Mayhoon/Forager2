package com.mygdx.networking;

import com.badlogic.gdx.math.Vector3;

public class ServerClientWrapper {
    private KryoServer server;
    private KryoClient client;
    private boolean isServer, isClient;

    public ServerClientWrapper(KryoServer server) {
        this.server = server;
        isServer = true;
    }

    public ServerClientWrapper(KryoClient client) {
        this.client = client;
        isClient = true;
    }

    public void setPosition(Vector3 playerPosition) {
        float playerX = playerPosition.x;
        float playerY = playerPosition.y;

        if (isServer) {
            server.getNetworkData().ownPositionX = playerX;
            server.getNetworkData().ownPositionY = playerY;
        } else if (isClient) {
            client.getNetworkData().ownPositionX = playerX;
            client.getNetworkData().ownPositionY = playerY;
        }
    }

    public float getOpponentPositionX() {
        if (isServer) {
            return server.getNetworkData().otherPositionX;
        } else {
            return client.getNetworkData().otherPositionX;
        }
    }

    public float getOpponentPositionY() {
        if (isServer) {
            return server.getNetworkData().otherPositionY;
        } else {
            return client.getNetworkData().otherPositionY;
        }
    }
}
