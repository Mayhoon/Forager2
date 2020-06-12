package networking;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

public class KryoClient extends NetworkEntity {
    private Client client;
    private CharacterData player;
    private CharacterData opponent;

    private String ip = "localhost";
    public boolean connected;

    public KryoClient() {
        client = new Client();
        player = new CharacterData();
        opponent = new CharacterData();

        //Register classes
        Kryo kryo = client.getKryo();
        ClassRegistry classRegistry = new ClassRegistry();
        kryo = classRegistry.addClassesTo(kryo);
    }

    public void start() {
        try {
            client.start();
            client.connect(5000, ip, 54555, 54777);
        } catch (IOException e) {
            e.printStackTrace();
        }
        connected = true;

        client.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof CharacterData) {
                    opponent = (CharacterData) object;
                }
            }
        });
    }

    @Override
    public void stop() {

    }

    @Override
    public CharacterData opponent() {
        return opponent;
    }

    @Override
    public void sendTCP(CharacterData data) {
        client.sendTCP(data);
    }
}
