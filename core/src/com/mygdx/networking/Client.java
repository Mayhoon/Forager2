package com.mygdx.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private boolean running;

    //Gets called by thread.start()
    public void run() {
        running = true;
        try {
            startConnection("localhost", 6666);
            while (running) {
                sendMessage();
                receiveMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() {
        System.out.println("Sending message to the server...");
        out.println("Nice to meet you!");
        out.flush();
    }

    private void receiveMessage() throws IOException {
        System.out.println("Receiving message from the server...");
        String receivedMessage = in.readLine();
        System.out.println("Server: " + receivedMessage);

        System.out.println("RECEIVED");
        if (receivedMessage.equals("end")) {
            stopConnection();
        }
    }

    public void startConnection(String ip, int port) throws IOException {
        System.out.println("Initiate connection...");
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void stopConnection() throws IOException {
        running = false;
        System.out.println("Closing connections...");
        in.close();
        out.close();
        clientSocket.close();
        System.out.println("Connections closed!");
    }
}
