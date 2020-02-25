package com.mygdx.networking;

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

    public void sendPosition(int position) {
        if (server != null) {
            server.ownPositionX = position;
        } else if (client != null) {
            client.ownPositionX = position;
        }
    }

    public int getOpponentPosition() {
        if (server != null) {
            return server.otherPositionX;
        } else if (client != null) {
            return client.otherPositionX;
        }
        return 11111;
    }
}
