package networking;

public class ServerClientWrapper {
    private KryoServer server;
    private KryoClient client;
    public boolean isServer, isClient;

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

    public NetworkData ownData() {
        if (isServer) {
            return server.getOwnData();
        } else {
            return client.getOwnData();
        }
    }

    public NetworkData opponentData() {
        if(isServer) {
            return server.getOpponentData();
        }else {
            return client.getOpponentData();
        }
    }
}
