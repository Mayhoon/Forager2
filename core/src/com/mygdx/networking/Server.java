package com.mygdx.networking;

import com.mygdx.config.Color;
import com.mygdx.stages.hud.ServerHud;
import com.mygdx.tools.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends NetworkInterface implements Runnable {
    public boolean running;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter OUT_printWriter;
    private BufferedReader IN_bufferedReader;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private ServerHud serverHud;

    public Server(ServerHud serverHud) {
        super(true);
        this.serverHud = serverHud;
    }

    @Override
    public void run() {
        try {
            start();
            while (running) {
                sendMessage();
                receiveMessage();
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
//        OUT_printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
//        IN_bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        Logger.log("Streams setup");
        running = true;
    }

    public void sendMessage() throws IOException {
//        OUT_printWriter.print(super.getOtherX());
//        OUT_printWriter.flush();
        System.out.println("S");
        dataOutputStream.writeInt(ownPositionX);
        dataOutputStream.flush();
    }

    private void receiveMessage() throws IOException {
        System.out.println("R");
        otherPositionX = dataInputStream.readInt();
//        int receivedMessage = IN_bufferedReader.read();
    }

    private void stopConnections() throws IOException {
        Logger.log(Color.ANSI_PURPLE + "Closing connections..." + Color.ANSI_RESET);
        running = false;
        IN_bufferedReader.close();
        OUT_printWriter.close();
        clientSocket.close();
        serverSocket.close();
        Logger.log("Connections closed");
    }
}