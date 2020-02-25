package com.mygdx.networking;

public class NetworkInterface {
    boolean isServer;
    public int ownPositionX = 0;
    public int otherPositionX = 0;

    public NetworkInterface(boolean isServer) {
        this.isServer = isServer;

    }
}
