package com.mygdx.networking;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;
import java.net.InetAddress;

public class KryoClient {
    private Client kryoClient;
    private NetworkData networkData;
    public boolean running;

    public KryoClient() {
        kryoClient = new Client();
        networkData = new NetworkData();

        //Register classes
        Kryo kryo = kryoClient.getKryo();
        kryo.register(NetworkData.class);
    }

    public void start(String ip) throws IOException {
        InetAddress address = kryoClient.discoverHost(54777, 5000);
        System.out.println("SERVER found with ip address: " + address);

        kryoClient.start();
        kryoClient.connect(5000, ip, 54555, 54777);
        running = true;
        kryoClient.sendTCP(networkData);

        kryoClient.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof NetworkData) {
                    NetworkData response = (NetworkData) object;

                    networkData.otherPositionX = response.ownPositionX;
                    networkData.otherPositionY = response.ownPositionY;

                    connection.sendTCP(networkData);
                }
            }
        });
    }

    public NetworkData getNetworkData() {
        return networkData;
    }
}
