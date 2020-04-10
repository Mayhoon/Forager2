package com.mygdx.networking;

import com.mygdx.config.Color;
import com.mygdx.stages.hud.ServerHud;
import com.mygdx.tools.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends NetworkData implements Runnable {
    public boolean running;
    private ServerHud serverHud;

    private Socket clientSocket;
    private ServerSocket serverSocket;

    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public Server(ServerHud serverHud) {
        this.serverHud = serverHud;
    }

    @Override
    public void run() {
        try {
            start();
            while (running) {
                receiveMessage();
                sendMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {
        Logger.log("Waiting for connections...");
        serverHud.connectionStatus = "Waiting for connections";

        serverSocket = new ServerSocket(6666);
        clientSocket = serverSocket.accept();

        serverHud.connectionStatus = "Connected!";
        Logger.log(clientSocket.getInetAddress().getHostName() + " has connected");
        dataInputStream = new DataInputStream(clientSocket.getInputStream());
        dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        Logger.log("Streams setup");
        running = true;
    }

    public void sendMessage() throws IOException {
        // System.out.println("S");
        dataOutputStream.writeFloat(ownPositionX);
        dataOutputStream.writeFloat(ownPositionY);
        dataOutputStream.flush();
    }

    private void receiveMessage() throws IOException {
        // System.out.println("R");
        otherPositionX = dataInputStream.readFloat();
        otherPositionY = dataInputStream.readFloat();
    }

    private void stopConnections() throws IOException {
        Logger.log(Color.ANSI_PURPLE + "Closing connections..." + Color.ANSI_RESET);
        running = false;
        clientSocket.close();
        serverSocket.close();
        Logger.log("Connections closed");
    }
}