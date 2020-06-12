package networking;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class KryoServer extends NetworkEntity {
    private Server server;
    private CharacterData player;
    private CharacterData opponent;

    public boolean running;

    public KryoServer() {
        server = new Server();
        player = new CharacterData();
        opponent = new CharacterData();

        //Register classes
        Kryo kryo = server.getKryo();
        ClassRegistry classRegistry = new ClassRegistry();
        kryo = classRegistry.addClassesTo(kryo);
    }

    public void start() {
        try {
            server.start();
            server.bind(54555, 54777);
        } catch (IOException e) {
            e.printStackTrace();
        }

        server.addListener(new Listener() {
            @Override
            public void connected(Connection connection) {
                super.connected(connection);
                running = true;
            }

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
        server.sendToAllTCP(data);
    }
}
