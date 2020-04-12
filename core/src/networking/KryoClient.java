package networking;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

public class KryoClient {
    private Client client;
    private NetworkData ownData;
    private NetworkData opponentData;
    public boolean running;

    public KryoClient() {
        client = new Client();
        ownData = new NetworkData();
        opponentData = new NetworkData();

        //Register classes
        Kryo kryo = client.getKryo();
        ClassRegistry classRegistry = new ClassRegistry();
        kryo = classRegistry.addClassesTo(kryo);
    }

    public void start(String ip) throws IOException {
        client.start();
        client.connect(5000, ip, 54555, 54777);
        running = true;

        client.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof NetworkData) {
                    opponentData = (NetworkData) object;
                }
            }
        });
    }

    public void sendTCP() {
        client.sendTCP(ownData);
    }

    public NetworkData getOwnData() {
        return ownData;
    }

    public NetworkData getOpponentData() {
        return opponentData;
    }
}
