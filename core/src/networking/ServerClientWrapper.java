package networking;

import Enums.AnimationState;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class ServerClientWrapper {
    private KryoServer server;
    private KryoClient client;
    private boolean isServer, isClient;

    public ServerClientWrapper(KryoServer server) {
        this.server = server;
        isServer = true;
    }

    public ServerClientWrapper(KryoClient client) {
        this.client = client;
        isClient = true;
    }

    public void sendTCP() {
        if (isServer) {
            server.sendTCP();
        } else {
            client.sendTCP();
        }
    }

    public NetworkData data() {
        if (isServer) {
            return server.getNetworkData();
        } else {
            return client.getNetworkData();
        }
    }
}