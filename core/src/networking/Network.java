package networking;

public class Network {
    private NetworkEntity networkEntity;
    private CharacterData playerData;

    public Network(NetworkType type) {
        playerData = new CharacterData();

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

    public CharacterData opponent() {
        return networkEntity.opponent();
    }

    public CharacterData player() {
        return playerData;
    }
}
