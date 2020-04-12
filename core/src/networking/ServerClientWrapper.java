package networking;

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
