package com.mygdx.networking;

import com.mygdx.config.Color;
import com.mygdx.stages.hud.ServerHud;
import com.mygdx.tools.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends NetworkInterface implements Runnable {
    public boolean running;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter OUT_printWriter;
    private BufferedReader IN_bufferedReader;
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

        OUT_printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        IN_bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        Logger.log("Streams setup");

        running = true;
    }

    private void receiveMessage() throws IOException {
        Logger.log("Receiving message from the client...");
        String receivedMessage = IN_bufferedReader.readLine();
        Logger.log("Client: " + receivedMessage);

        if (receivedMessage.equals("end")) {
            stopConnections();
        }
    }

    private void sendMessage() {
        Logger.log("Sending message to the client...");
        OUT_printWriter.println();
        OUT_printWriter.flush();
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