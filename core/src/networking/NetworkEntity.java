package networking;

public abstract class NetworkEntity {

    public abstract void start();

    public abstract void stop();

    public abstract State opponent();

    public abstract void sendTCP(State data);
}
