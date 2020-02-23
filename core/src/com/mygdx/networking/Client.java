package com.mygdx.networking;

import com.mygdx.stages.hud.ClientHud;
import com.mygdx.tools.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends NetworkInterface implements Runnable {
    public boolean running;
    private Socket clientSocket;
    private PrintWriter OUT_printWriter;
    private BufferedReader IN_bufferedReader;
    private ClientHud clientHud;

    public Client(ClientHud clientHud) {
        super(false);
        this.clientHud = clientHud;
    }

    public void run() {
        try {
            clientHud.connectionStatus = "Connecting...";
            startConnection(clientHud.serverIp, 6666);
            clientHud.connectionStatus = "Connected to " + clientHud.serverIp;
            running = true;
            while (running) {
                sendMessage();
                receiveMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() {
        Logger.log("Sending message to the server...");
        OUT_printWriter.println("Nice to meet you!");
        OUT_printWriter.flush();
    }

    private void receiveMessage() throws IOException {
        String receivedMessage = IN_bufferedReader.readLine();
        Logger.log("Server: " + receivedMessage);

        if (receivedMessage.equals("end")) {
            stopConnection();
        }
    }

    public void startConnection(String ip, int port) throws IOException {
        Logger.log("Initiate connection...");
        clientSocket = new Socket(ip, port);
        OUT_printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        IN_bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void stopConnection() throws IOException {
        running = false;
        Logger.log("Closing connections...");
        IN_bufferedReader.close();
        OUT_printWriter.close();
        clientSocket.close();
        Logger.log("Connections closed!");
    }
}
