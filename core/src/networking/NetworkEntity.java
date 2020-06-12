package networking;

public abstract class NetworkEntity {

    public abstract void start();

    public abstract void stop();

    public abstract CharacterData opponent();

    public abstract void sendTCP(CharacterData data);
}
