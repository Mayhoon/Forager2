package com.mygdx.networking;

import com.mygdx.stages.hud.ClientHud;
import com.mygdx.tools.Logger;

import java.io.*;
import java.net.Socket;

public class Client extends NetworkData implements Runnable {
    public boolean running;
    private ClientHud clientHud;
    private Socket clientSocket;

    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    public Client(ClientHud clientHud) {
        this.clientHud = clientHud;
    }

    @Override
    public void run() {
        try {
            Logger.log("Connecting...");
            startConnection(clientHud.serverIp, 6666);
            clientHud.connectionStatus = "Connected to " + clientHud.serverIp;
            while (running) {
                sendMessage();
                receiveMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() throws IOException {
        System.out.println("S");
        dataOutputStream.writeInt(ownPositionX);
        dataOutputStream.writeInt(ownPositionY);
        dataOutputStream.flush();
    }

    private void receiveMessage() throws IOException {
        System.out.println("R");
        otherPositionX = dataInputStream.readInt();
        otherPositionY = dataInputStream.readInt();
    }

    public void startConnection(String ip, int port) throws IOException {
        Logger.log("Initiate connection...");
        clientSocket = new Socket(ip, port);

//        OUT_printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
//        IN_bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        dataInputStream = new DataInputStream(clientSocket.getInputStream());
        dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

        Logger.log("Running");
        running = true;
    }

    public void stopConnection() throws IOException {
        running = false;
        Logger.log("Closing connections...");
        clientSocket.close();
        Logger.log("Connections closed!");
    }
}
