package com.mygdx.networking;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class KryoServer {
    private Server server;
    private NetworkData networkData;
    public boolean running;

    public KryoServer() {
        server = new Server();
        networkData = new NetworkData();

        //Register classes
        Kryo kryo = server.getKryo();
        kryo.register(NetworkData.class);
    }

    public void start() throws IOException {
        server.start();
        server.bind(54555, 54777);

        server.addListener(new Listener() {
            @Override
            public void connected(Connection connection) {
                super.connected(connection);
                running = true;
            }

            public void received(Connection connection, Object object) {
                if (object instanceof NetworkData) {
                    NetworkData response = (NetworkData) object;
                    networkData.otherPositionX = response.ownPositionX;
                    networkData.otherPositionY = response.ownPositionY;
                }
            }
        });
    }
    public void sendTCP() {
        server.sendToAllTCP(networkData);
    }

    public NetworkData getNetworkData() {
        return networkData;
    }
}
