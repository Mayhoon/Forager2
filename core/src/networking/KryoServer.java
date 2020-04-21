package networking;

import Enums.AnimationName;
import Enums.Direction;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class KryoServer {
    private Server server;
    private NetworkData ownData;
    private NetworkData opponentData;
    public boolean running;

    public KryoServer() {
        server = new Server();
        ownData = new NetworkData();
        opponentData = new NetworkData();

        //Register classes
        Kryo kryo = server.getKryo();
        ClassRegistry classRegistry = new ClassRegistry();
        kryo = classRegistry.addClassesTo(kryo);
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

            //Get informations from opponent
            public void received(Connection connection, Object object) {
                if (object instanceof NetworkData) {
                    opponentData = (NetworkData) object;
                }
            }
        });
    }

    public void sendTCP() {
        server.sendToAllTCP(ownData);
    }

    public NetworkData getOwnData() {
        return ownData;
    }

    public NetworkData getOpponentData() {
        return opponentData;
    }
}
