package networking;

public class Network {
    private NetworkEntity networkEntity;
    private State playerData;

    public Network(NetworkType type) {
        playerData = new State();

        if (type.equals(NetworkType.CLIENT)) {
            networkEntity = new KryoClient();
            networkEntity.start();
        } else if (type.equals(NetworkType.HOST)) {
            networkEntity = new KryoServer();
            networkEntity.start();
        }
    }

    public void sendData() {
        networkEntity.sendTCP(playerData);
    }

    public State opponent() {
        return networkEntity.opponent();
    }

    public State player() {
        return playerData;
    }
}
