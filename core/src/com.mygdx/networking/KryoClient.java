package com.mygdx.networking;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

public class KryoClient {
    private Client client;
    private NetworkData networkData;
    public boolean running;

    public KryoClient() {
        client = new Client();
        networkData = new NetworkData();

        //Register classes
        Kryo kryo = client.getKryo();
        kryo.register(NetworkData.class);
    }

    public void start(String ip) throws IOException {
//        Discover hosted games within the network
//        InetAddress address = kryoClient.discoverHost(54777, 5000);
//        System.out.println("SERVER found with ip address: " + address);

        client.start();
        client.connect(5000, ip, 54555, 54777);
        running = true;

        client.addListener(new Listener() {
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
        client.sendTCP(networkData);
    }

    public NetworkData getNetworkData() {
        return networkData;
    }
}
