package com.mygdx.networking;

import com.badlogic.gdx.math.Vector3;

// This class contains either the client or server
// and provides methods to send/ read data from the NetworkInterface
// which holds data describing the current game state
public class ServerClientWrapper {
    private Server server;
    private Client client;

    public ServerClientWrapper(Server server) {
        this.server = server;
    }

    public ServerClientWrapper(Client client) {
        this.client = client;
    }

    public boolean isServer() {
        if (server != null) {
            return true;
        } else if (client != null) {
            return false;
        }
        return false;
    }

    public void sendPosition(Vector3 playerPosition) {
        float playerX = playerPosition.x;
        float playerY = playerPosition.y;

        if (server != null) {
            server.ownPositionX = playerX;
            server.ownPositionY = playerY;
        } else if (client != null) {
            client.ownPositionX = playerX;
            client.ownPositionY = playerY;
        }
    }

    public float getOpponentPositionX() {
        if (server != null) {
            return server.otherPositionX;
        } else if (client != null) {
            return client.otherPositionX;
        }
        return 11111;
    }

    public float getOpponentPositionY() {
        if (server != null) {
            return server.otherPositionY;
        } else if (client != null) {
            return client.otherPositionY;
        }
        return 11111;
    }
}
